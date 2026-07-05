package com.example.dzakwan_luck.Home.mutasi

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dzakwan_luck.R

class MutasiAdapter(private val listMutasi: List<MutasiModel>) : RecyclerView.Adapter<MutasiAdapter.MutasiViewHolder>() {

    inner class MutasiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivMutasi: ImageView = itemView.findViewById(R.id.ivMutasi)
        val tvBadgeStatus: TextView = itemView.findViewById(R.id.tvBadgeStatus)
        val tvNamaDanKode: TextView = itemView.findViewById(R.id.tvNamaDanKode)
        val tvKeterangan: TextView = itemView.findViewById(R.id.tvKeteranganMutasi)
        val tvTanggal: TextView = itemView.findViewById(R.id.tvTanggalMutasi)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MutasiViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_mutasi, parent, false)
        return MutasiViewHolder(view)
    }

    override fun onBindViewHolder(holder: MutasiViewHolder, position: Int) {
        val mutasi = listMutasi[position]

        // Gabungkan nama dan kode seperti di Data Aset
        holder.tvNamaDanKode.text = "${mutasi.namaAset}\n(${mutasi.kodeAset})"
        holder.tvKeterangan.text = mutasi.keterangan
        holder.tvTanggal.text = mutasi.tanggal
        holder.tvBadgeStatus.text = mutasi.jenisMutasi

        // Ganti warna badge sesuai jenis mutasi
        when (mutasi.jenisMutasi) {
            "Peminjaman" -> holder.tvBadgeStatus.setBackgroundColor(Color.parseColor("#4CAF50")) // Hijau
            "Pengembalian" -> holder.tvBadgeStatus.setBackgroundColor(Color.parseColor("#FBC02D")) // Kuning
            "Perbaikan" -> holder.tvBadgeStatus.setBackgroundColor(Color.parseColor("#D32F2F")) // Merah
            else -> holder.tvBadgeStatus.setBackgroundColor(Color.parseColor("#757575")) // Abu-abu
        }

        // Load Gambar
        Glide.with(holder.itemView.context)
            .load(mutasi.imageUrl)
            .into(holder.ivMutasi)

        // Event Klik memunculkan Toast
        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Detail Mutasi: ${mutasi.namaAset}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = listMutasi.size
}