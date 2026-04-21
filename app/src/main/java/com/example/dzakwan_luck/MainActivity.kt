package com.example.dzakwan_luck

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.example.dzakwan_luck.databinding.ActivityMainBinding // Sesuaikan dengan nama layoutmu
import com.example.dzakwan_luck.pertemuan3.P3Activity
import com.example.dzakwan_luck.pertemuan4.CustomOneActivity
import com.example.dzakwan_luck.pertemuan4.CustomTwoActivity
import com.example.dzakwan_luck.pertemuan6.WebBinaDesaActivity
import com.google.android.material.snackbar.Snackbar
import kotlin.jvm.java

class MainActivity : AppCompatActivity() {

    // Inisialisasi ViewBinding
    private lateinit var binding: ActivityMainBinding

    // Tag untuk Logcat Lifecycle
    private val TAG = "Lifecycle_Log"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "onCreate: Halaman Utama Dibuat")

        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        // 1. Menghidupkan Toolbar
        setSupportActionBar(binding.toolbarMain)
        supportActionBar?.apply {
            title = "Dashboard Bina Desa"
            // Catatan: Di MainActivity biasanya tidak butuh panah Back
            // karena ini halaman utama. Panah back kita pasang di halaman Web nanti.
        }

        // 2. Aksi ketika tombol "Buka Web" diklik
        binding.btnOpenWeb.setOnClickListener {
            // Pindah ke halaman WebView
            val intent = Intent(this, WebBinaDesaActivity::class.java)
            startActivity(intent)
        }
        // ==========================================
        // TOMBOL 1: Pindah ke Kalkulator (Pertemuan 2)
        // ==========================================
        binding.btnCalculator.setOnClickListener {
            val intent = Intent(this, P2Activity::class.java) // Sesuaikan nama class Pertemuan 2
            startActivity(intent)
        }

        // ==========================================
        // TOMBOL 2: Data Aset (Halaman Custom 1)
        // ==========================================
        binding.btnJobVacancy.setOnClickListener {
            val intent = Intent(this, CustomOneActivity::class.java)
            // Menyisipkan data (Kunci, Nilai) untuk Inventaris
            intent.putExtra("EXTRA_TITLE", "Manajemen Data Aset")
            intent.putExtra("EXTRA_DESC", "Pantau dan kelola seluruh daftar aset milik desa, mulai dari peralatan kantor hingga infrastruktur, secara akurat.")
            startActivity(intent)
        }

        // ==========================================
        // TOMBOL 3: Tentang Aplikasi (Halaman Custom 2)
        // ==========================================
        binding.btnCareerTips.setOnClickListener {
            val intent = Intent(this, CustomTwoActivity::class.java)
            // Menyisipkan informasi mengenai project Bina Desa
            intent.putExtra("EXTRA_TITLE", "Tentang Bina Desa")
            intent.putExtra("EXTRA_DESC", "Aplikasi Sistem Informasi Inventaris Aset Desa yang dirancang untuk meningkatkan transparansi dan akuntabilitas pengelolaan aset.")
            startActivity(intent)
        }

        // ==========================================
        // TOMBOL 4: Logout (AlertDialog & Snackbar)
        // ==========================================
        binding.btnLogout.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Konfirmasi Logout")
            builder.setMessage("Apakah Anda yakin ingin keluar dari aplikasi Inventaris Desa?")

            // Jika tombol "Ya" diklik
            builder.setPositiveButton("Ya") { dialog, which ->
                dialog.dismiss()
                sharedPref.edit {
                    clear()
                }
                val intent = Intent(this, P3Activity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
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

    // Lifecycle: onStart (Sistem mulai menampilkan halaman ke user)
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: Halaman Utama Mulai Terlihat")
    }

    // Lifecycle: onDestroy (Sistem menghancurkan halaman dari memori)
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: Halaman Utama Dihancurkan")
    }
}