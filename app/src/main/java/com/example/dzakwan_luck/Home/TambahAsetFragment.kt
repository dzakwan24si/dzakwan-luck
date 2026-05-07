package com.example.dzakwan_luck.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.dzakwan_luck.databinding.FragmentTambahAsetBinding // Sesuaikan package

class TambahAsetFragment : Fragment() {

    private var _binding: FragmentTambahAsetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTambahAsetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Aksi saat tombol simpan diklik
        binding.btnSimpanAset.setOnClickListener {
            val nama = binding.etNamaAset.text.toString()

            if (nama.isEmpty()) {
                // Contoh penggunaan error helper bawaan TextInputLayout
                binding.etNamaAset.error = "Nama aset tidak boleh kosong!"
            } else {
                Toast.makeText(requireContext(), "Aset $nama berhasil disimpan!", Toast.LENGTH_SHORT).show()
                // Kembali ke halaman sebelumnya secara otomatis
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}