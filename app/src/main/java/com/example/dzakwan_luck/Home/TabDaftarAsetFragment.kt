package com.example.dzakwan_luck.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager // Import ini
import com.example.dzakwan_luck.R
import com.example.dzakwan_luck.databinding.FragmentTabDaftarAsetBinding

class TabDaftarAsetFragment : Fragment() {
    private var _binding: FragmentTabDaftarAsetBinding? = null
    private val binding get() = _binding!!

    // Tambahkan URL gambar di ujung setiap data
    private val asetList = listOf(
        AsetModel("AST-005", "Server Rakitan #5", "Elektronik", "Baik", "Rp 6.035.309", "https://loremflickr.com/400/300/server,datacenter/all?lock=1"),
        AsetModel("AST-006", "Brankas Uang #6", "Kendaraan", "Rusak Ringan", "Rp 24.951.541", "https://loremflickr.com/400/300/safe,vault/all?lock=1"),
        AsetModel("AST-007", "Server Rakitan #7", "Mesin", "Rusak Berat", "Rp 18.122.554", "https://loremflickr.com/400/300/server,rack/all?lock=2"),
        AsetModel("AST-008", "Lemari Besi #8", "Furniture", "Rusak Berat", "Rp 3.331.131", "https://loremflickr.com/400/300/cabinet,locker/all?lock=1"),
        AsetModel("AST-009", "Laptop Asus ROG #9", "Elektronik", "Rusak Berat", "Rp 15.000.000", "https://loremflickr.com/400/300/laptop,gaming/all?lock=1"),
        AsetModel("AST-010", "Laptop Asus ROG #10", "Elektronik", "Rusak Berat", "Rp 15.000.000", "https://loremflickr.com/400/300/laptop,computer/all?lock=2"),
        AsetModel("AST-011", "Meja Kantor #11", "Furniture", "Rusak Ringan", "Rp 6.000.000", "https://loremflickr.com/400/300/desk,office/all?lock=1")
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTabDaftarAsetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val asetAdapter = AsetAdapter(asetList) { selectedItem ->
            Toast.makeText(requireContext(), "Aksi Detail: ${selectedItem.nama}", Toast.LENGTH_SHORT).show()
        }

        binding.rvAset.apply {
            // Ubah menjadi GridLayout 2 kolom di sini!
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = asetAdapter
        }
        // Logika pindah ke halaman form tambah aset
        binding.btnTambahAset.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, TambahAsetFragment())
                .addToBackStack(null) // Agar bisa tombol Back
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}