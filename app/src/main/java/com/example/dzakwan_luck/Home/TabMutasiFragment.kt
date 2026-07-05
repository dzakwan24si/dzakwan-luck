package com.example.dzakwan_luck.Home

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dzakwan_luck.Home.mutasi.MutasiAdapter
import com.example.dzakwan_luck.Home.MutasiFormActivity
import com.example.dzakwan_luck.Home.mutasi.MutasiModel
import com.example.dzakwan_luck.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

class TabMutasiFragment : Fragment() {

    private lateinit var rvMutasi: RecyclerView
    private lateinit var mutasiAdapter: MutasiAdapter
    private var fullDataList = listOf<MutasiModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_tab_mutasi, container, false)

        rvMutasi = view.findViewById(R.id.rvMutasi)
        rvMutasi.layoutManager = GridLayoutManager(requireContext(), 2)

        // Pindah ke Halaman Form Tambah Mutasi
        val btnTambah = view.findViewById<MaterialButton>(R.id.btnTambahMutasi)
        btnTambah.setOnClickListener {
            startActivity(Intent(requireContext(), MutasiFormActivity::class.java))
        }

        // Data Dummy
        fullDataList = listOf(
            MutasiModel("05/07/2026", "Server Rakitan #5", "AST-005", "Peminjaman", "Dipinjam oleh Tim IT", "https://images.unsplash.com/photo-1558494949-ef010cbdcc31?w=500"),
            MutasiModel("03/07/2026", "Brankas Uang #6", "AST-006", "Pengembalian", "Dikembalikan dari ruang arsip", "https://images.unsplash.com/photo-1563207153-f408bfb02fcb?w=500"),
            MutasiModel("01/07/2026", "Server Rakitan #7", "AST-007", "Perbaikan", "Servis motherboard rusak", "https://images.unsplash.com/photo-1550751827-4bd374c3f58b?w=500"),
            MutasiModel("28/06/2026", "Lemari Besi #8", "AST-008", "Peminjaman", "Dipindahkan ke gudang baru", "https://images.unsplash.com/photo-1595428774223-ef52624120d2?w=500")
        )

        // Set adapter awal (tampilkan semua)
        mutasiAdapter = MutasiAdapter(fullDataList)
        rvMutasi.adapter = mutasiAdapter

        // Deklarasi Komponen Filter
        val cvSemua = view.findViewById<MaterialCardView>(R.id.cvFilterSemua)
        val tvSemua = view.findViewById<TextView>(R.id.tvFilterSemua)
        val cvPeminjaman = view.findViewById<MaterialCardView>(R.id.cvFilterPeminjaman)
        val tvPeminjaman = view.findViewById<TextView>(R.id.tvFilterPeminjaman)
        val cvPengembalian = view.findViewById<MaterialCardView>(R.id.cvFilterPengembalian)
        val tvPengembalian = view.findViewById<TextView>(R.id.tvFilterPengembalian)
        val cvPerbaikan = view.findViewById<MaterialCardView>(R.id.cvFilterPerbaikan)
        val tvPerbaikan = view.findViewById<TextView>(R.id.tvFilterPerbaikan)

        // Fungsi Reset Warna Filter ke Abu-abu (Tidak Aktif)
        fun resetFilterColors() {
            cvSemua.setCardBackgroundColor(Color.parseColor("#F5F5F5"))
            tvSemua.setTextColor(Color.parseColor("#757575"))
            tvSemua.text = "Semua Mutasi"

            cvPeminjaman.setCardBackgroundColor(Color.parseColor("#F5F5F5"))
            tvPeminjaman.setTextColor(Color.parseColor("#757575"))
            tvPeminjaman.text = "Peminjaman"

            cvPengembalian.setCardBackgroundColor(Color.parseColor("#F5F5F5"))
            tvPengembalian.setTextColor(Color.parseColor("#757575"))
            tvPengembalian.text = "Pengembalian"

            cvPerbaikan.setCardBackgroundColor(Color.parseColor("#F5F5F5"))
            tvPerbaikan.setTextColor(Color.parseColor("#757575"))
            tvPerbaikan.text = "Perbaikan"
        }

        // Aksi Klik Filter
        cvSemua.setOnClickListener {
            resetFilterColors()
            cvSemua.setCardBackgroundColor(Color.parseColor("#E8EAF6"))
            tvSemua.setTextColor(Color.parseColor("#3F51B5"))
            tvSemua.text = "✓ Semua Mutasi"
            mutasiAdapter = MutasiAdapter(fullDataList)
            rvMutasi.adapter = mutasiAdapter
        }

        cvPeminjaman.setOnClickListener {
            resetFilterColors()
            cvPeminjaman.setCardBackgroundColor(Color.parseColor("#4CAF50"))
            tvPeminjaman.setTextColor(Color.parseColor("#FFFFFF"))
            tvPeminjaman.text = "✓ Peminjaman"
            val filteredList = fullDataList.filter { it.jenisMutasi == "Peminjaman" }
            mutasiAdapter = MutasiAdapter(filteredList)
            rvMutasi.adapter = mutasiAdapter
        }

        cvPengembalian.setOnClickListener {
            resetFilterColors()
            cvPengembalian.setCardBackgroundColor(Color.parseColor("#FFC107"))
            tvPengembalian.setTextColor(Color.parseColor("#FFFFFF"))
            tvPengembalian.text = "✓ Pengembalian"
            val filteredList = fullDataList.filter { it.jenisMutasi == "Pengembalian" }
            mutasiAdapter = MutasiAdapter(filteredList)
            rvMutasi.adapter = mutasiAdapter
        }

        cvPerbaikan.setOnClickListener {
            resetFilterColors()
            cvPerbaikan.setCardBackgroundColor(Color.parseColor("#F44336"))
            tvPerbaikan.setTextColor(Color.parseColor("#FFFFFF"))
            tvPerbaikan.text = "✓ Perbaikan"
            val filteredList = fullDataList.filter { it.jenisMutasi == "Perbaikan" }
            mutasiAdapter = MutasiAdapter(filteredList)
            rvMutasi.adapter = mutasiAdapter
        }

        return view
    }
}