package com.example.dzakwan_luck.Home.pemeliharaan

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dzakwan_luck.Home.pemeliharaan.PemeliharaanFormActivity
import com.example.dzakwan_luck.data.entity.PemeliharaanEntity
import com.example.dzakwan_luck.databinding.ItemPemeliharaanBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class PemeliharaanAdapter(
    private val listData: List<PemeliharaanEntity>,
    private val fragment: TabPemeliharaanFragment // Untuk akses fungsi Hapus
) : RecyclerView.Adapter<PemeliharaanAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemPemeliharaanBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPemeliharaanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listData[position]

        holder.binding.tvTanggalPemeliharaan.text = item.tanggal
        holder.binding.tvNamaAset.text = "${item.namaAset}\n${item.kodeAset}"
        holder.binding.tvTindakan.text = item.tindakan
        holder.binding.tvBiaya.text = item.biaya

        // Tampilkan/Sembunyikan Badge File
        if (item.adaBukti) {
            holder.binding.tvBadgeBukti.visibility = View.VISIBLE
        } else {
            holder.binding.tvBadgeBukti.visibility = View.GONE
        }

        // Load Gambar Pakai Glide
        Glide.with(holder.itemView.context)
            .load(item.imageUrl)
            .centerCrop()
            .into(holder.binding.imgPemeliharaan)

        // Logika klik untuk mengedit data
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, PemeliharaanFormActivity::class.java).apply {
                // Semua properti dari Entity wajib dikirim
                putExtra("EXTRA_ID", item.id)
                putExtra("EXTRA_TANGGAL", item.tanggal)
                putExtra("EXTRA_NAMA_ASET", item.namaAset)
                putExtra("EXTRA_KODE_ASET", item.kodeAset)
                putExtra("EXTRA_TINDAKAN", item.tindakan)
                putExtra("EXTRA_BIAYA", item.biaya)
                putExtra("EXTRA_ADA_BUKTI", item.adaBukti)
                putExtra("EXTRA_IMAGE_URL", item.imageUrl)
            }
            holder.itemView.context.startActivity(intent)
        }

        // Fitur Hapus Data (Tekan Tahan Kartunya)
        holder.itemView.setOnLongClickListener {
            MaterialAlertDialogBuilder(holder.itemView.context)
                .setTitle("Hapus Data")
                .setMessage("Yakin ingin menghapus riwayat pemeliharaan ini?")
                .setPositiveButton("Hapus") { dialog, _ ->
                    fragment.hapusData(item)
                    dialog.dismiss()
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
            true
        }
    }

    override fun getItemCount(): Int = listData.size
}