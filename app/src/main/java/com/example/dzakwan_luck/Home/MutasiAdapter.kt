package com.example.dzakwan_luck.Home

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dzakwan_luck.databinding.ItemMutasiBinding

class MutasiAdapter(
    private val list: List<MutasiModel>,
    private val onItemClick: (MutasiModel) -> Unit
) : RecyclerView.Adapter<MutasiAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemMutasiBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMutasiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        with(holder.binding) {
            tvTanggalMutasi.text = item.tanggal
            tvNamaAsetMutasi.text = "${item.namaAset}\n${item.kodeAset}"
            tvKeterangan.text = item.keterangan
            tvJenisMutasi.text = item.jenisMutasi

            // Ubah warna badge sesuai dengan jenis mutasi di web
            when (item.jenisMutasi.lowercase()) {
                "penghapusan" -> tvJenisMutasi.setBackgroundColor(Color.parseColor("#DC3545")) // Merah
                "pemindahan", "peminjaman" -> tvJenisMutasi.setBackgroundColor(Color.parseColor("#0D6EFD")) // Biru
                "perubahan status" -> tvJenisMutasi.setBackgroundColor(Color.parseColor("#FFC107")) // Kuning
                "pengembalian" -> tvJenisMutasi.setBackgroundColor(Color.parseColor("#198754")) // Hijau
            }

            root.setOnClickListener { onItemClick(item) }
        }
    }

    override fun getItemCount(): Int = list.size
}