package com.example.dzakwan_luck.Home.pemeliharaan

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dzakwan_luck.Home.pemeliharaan.PemeliharaanFormActivity
import com.example.dzakwan_luck.data.AppDatabase
import com.example.dzakwan_luck.data.entity.PemeliharaanEntity
import com.example.dzakwan_luck.databinding.FragmentTabPemeliharaanBinding
import kotlinx.coroutines.launch

class TabPemeliharaanFragment : Fragment() {
    private var _binding: FragmentTabPemeliharaanBinding? = null
    private val binding get() = _binding!!

    private lateinit var db: AppDatabase
    private lateinit var adapter: PemeliharaanAdapter
    private val listPemeliharaan = mutableListOf<PemeliharaanEntity>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTabPemeliharaanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi Database Room
        db = AppDatabase.getInstance(requireContext())

        // Inisialisasi Adapter
        adapter = PemeliharaanAdapter(listPemeliharaan, this)

        // Gunakan GridLayout dengan 2 Kolom (Sesuai tugas minggu lalu)
        binding.rvPemeliharaan.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvPemeliharaan.adapter = adapter

        // Tombol Tambah membuka Form Baru
        binding.btnTambahPemeliharaan.setOnClickListener {
            startActivity(Intent(requireContext(), PemeliharaanFormActivity::class.java))
        }
    }

    // Mengambil data dari Database
    private fun fetchPemeliharaan() {
        lifecycleScope.launch {
            val data = db.pemeliharaanDao().getAllPemeliharaan()
            listPemeliharaan.clear()
            listPemeliharaan.addAll(data)
            adapter.notifyDataSetChanged()
        }
    }

    // Fungsi dipanggil oleh adapter saat kartu ditekan tahan
    fun hapusData(pemeliharaan: PemeliharaanEntity) {
        lifecycleScope.launch {
            db.pemeliharaanDao().deletePemeliharaan(pemeliharaan)
            fetchPemeliharaan()
        }
    }

    // Auto-refresh saat kembali dari Form
    override fun onResume() {
        super.onResume()
        fetchPemeliharaan()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}