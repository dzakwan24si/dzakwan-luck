package com.example.dzakwan_luck.Home

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide // Pastikan import Glide
import com.example.dzakwan_luck.databinding.ItemAsetBinding

class AsetAdapter(
    private val listAset: List<AsetModel>,
    private val onItemClick: (AsetModel) -> Unit
) : RecyclerView.Adapter<AsetAdapter.AsetViewHolder>() {

    inner class AsetViewHolder(val binding: ItemAsetBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsetViewHolder {
        val binding = ItemAsetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AsetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AsetViewHolder, position: Int) {
        val item = listAset[position]
        with(holder.binding) {
            tvNamaAset.text = "${item.nama} (${item.kode})"
            tvKategoriAset.text = item.kategori
            tvNilaiAset.text = item.nilai
            tvKondisi.text = item.kondisi

            // Mengatur warna badge
            when (item.kondisi.lowercase()) {
                "baik" -> tvKondisi.setBackgroundColor(Color.parseColor("#28A745"))
                "rusak ringan" -> tvKondisi.setBackgroundColor(Color.parseColor("#FFC107"))
                "rusak berat" -> tvKondisi.setBackgroundColor(Color.parseColor("#DC3545"))
            }

            // Memuat Gambar pakai Glide
            Glide.with(holder.itemView.context)
                .load(item.imageUrl)
                .centerCrop()
                .into(imgAset)

            root.setOnClickListener { onItemClick(item) }
        }
    }

    override fun getItemCount(): Int = listAset.size
}