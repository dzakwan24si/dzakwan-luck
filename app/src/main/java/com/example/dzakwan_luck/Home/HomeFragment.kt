package com.example.dzakwan_luck.Home

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.example.dzakwan_luck.Home.pertemuan3.P3Activity
import com.example.dzakwan_luck.Home.pertemuan4.CustomOneActivity
import com.example.dzakwan_luck.Home.pertemuan4.CustomTwoActivity
import com.example.dzakwan_luck.Home.pertemuan6.WebBinaDesaActivity
import com.example.dzakwan_luck.P2Activity
import com.example.dzakwan_luck.R
import com.example.dzakwan_luck.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sharedPref = requireContext().getSharedPreferences("user_pref", MODE_PRIVATE)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Home Inventaris"

        // 2. Aksi ketika tombol "Buka Web" diklik
        binding.btnOpenWeb.setOnClickListener {
            val intent = Intent(requireContext(), WebBinaDesaActivity::class.java)
            startActivity(intent)
        }
        // ==========================================
        // TOMBOL 1: Pindah ke Kalkulator (Pertemuan 2)
        // ==========================================
        binding.btnCalculator.setOnClickListener {
            val intent = Intent(requireContext(), P2Activity::class.java) // Sesuaikan nama class Pertemuan 2
            startActivity(intent)
        }

        // ==========================================
        // TOMBOL 2: Data Aset (Halaman Custom 1)
        // ==========================================
        binding.btnJobVacancy.setOnClickListener {
            val intent = Intent(requireContext(), CustomOneActivity::class.java)
            // Menyisipkan data (Kunci, Nilai) untuk Inventaris
            intent.putExtra("EXTRA_TITLE", "Manajemen Data Aset")
            intent.putExtra("EXTRA_DESC", "Pantau dan kelola seluruh daftar aset milik desa, mulai dari peralatan kantor hingga infrastruktur, secara akurat.")
            startActivity(intent)
        }

        // ==========================================
        // TOMBOL 3: Tentang Aplikasi (Halaman Custom 2)
        // ==========================================
        binding.btnCareerTips.setOnClickListener {
            val intent = Intent(requireContext(), CustomTwoActivity::class.java)
            // Menyisipkan informasi mengenai project Bina Desa
            intent.putExtra("EXTRA_TITLE", "Tentang Bina Desa")
            intent.putExtra("EXTRA_DESC", "Aplikasi Sistem Informasi Inventaris Aset Desa yang dirancang untuk meningkatkan transparansi dan akuntabilitas pengelolaan aset.")
            startActivity(intent)
        }

        // ==========================================
        // TOMBOL 4: Logout (AlertDialog & Snackbar)
        // ==========================================
        binding.btnLogout.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Konfirmasi Logout")
            builder.setMessage("Apakah Anda yakin ingin keluar dari aplikasi Inventaris Desa?")

            // Jika tombol "Ya" diklik
            builder.setPositiveButton("Ya") { dialog, which ->
                dialog.dismiss()
                sharedPref.edit {
                    clear()
                }
                val intent = Intent(requireContext(), P3Activity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                requireActivity().finish()
            }

            // Jika tombol "Batal" diklik
            builder.setNegativeButton("Batal") { dialog, which ->
                Snackbar.make(binding.root, "Logout dibatalkan", Snackbar.LENGTH_SHORT)
                    .setBackgroundTint(resources.getColor(R.color.primary_blue))
                    .show()
            }

            // Tampilkan Dialog
            val dialog = builder.create()
            dialog.show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}