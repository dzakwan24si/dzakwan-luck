package com.example.dzakwan_luck.Home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide // Import Glide
import com.example.dzakwan_luck.databinding.ItemPemeliharaanBinding

class PemeliharaanAdapter(
    private val list: List<PemeliharaanModel>,
    private val onItemClick: (PemeliharaanModel) -> Unit
) : RecyclerView.Adapter<PemeliharaanAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemPemeliharaanBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPemeliharaanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        with(holder.binding) {
            // Bind Teks
            tvTanggalPemeliharaan.text = item.tanggal
            tvNamaAset.text = "${item.namaAset}\n${item.kodeAset}"
            tvTindakan.text = item.tindakan
            tvBiaya.text = item.biaya

            // Logika Munculkan Badge File
            tvBadgeBukti.visibility = if (item.adaBukti) View.VISIBLE else View.GONE

            // Load Gambar pakai Glide (TAMBAHAN)
            Glide.with(holder.itemView.context)
                .load(item.imageUrl)
                .centerCrop()
                .into(imgPemeliharaan)

            root.setOnClickListener { onItemClick(item) }
        }
    }

    override fun getItemCount(): Int = list.size
}