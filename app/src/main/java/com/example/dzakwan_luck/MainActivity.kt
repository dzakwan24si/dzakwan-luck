package com.example.dzakwan_luck

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.dzakwan_luck.databinding.ActivityMainBinding // Sesuaikan dengan nama layoutmu
import com.example.dzakwan_luck.pertemuan3.P3Activity
import com.example.dzakwan_luck.pertemuan4.CustomOneActivity
import com.example.dzakwan_luck.pertemuan4.CustomTwoActivity
import com.google.android.material.snackbar.Snackbar

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
        // ==========================================
        // TOMBOL 1: Pindah ke Kalkulator (Pertemuan 2)
        // ==========================================
        binding.btnCalculator.setOnClickListener {
            val intent = Intent(this, P2Activity::class.java) // Sesuaikan nama class Pertemuan 2
            startActivity(intent)
        }

        // ==========================================
        // TOMBOL 2: Halaman Custom 1 (Membawa Data Extra)
        // ==========================================
        binding.btnJobVacancy.setOnClickListener {
            val intent = Intent(this, CustomOneActivity::class.java)
            // Menyisipkan data (Kunci, Nilai)
            intent.putExtra("EXTRA_TITLE", "Lowongan Terbaru")
            intent.putExtra("EXTRA_DESC", "Temukan pekerjaan impianmu yang sesuai dengan keahlianmu di sini!")
            startActivity(intent)
        }

        // ==========================================
        // TOMBOL 3: Halaman Custom 2 (Membawa Data Extra)
        // ==========================================
        binding.btnCareerTips.setOnClickListener {
            val intent = Intent(this, CustomTwoActivity::class.java)
            intent.putExtra("EXTRA_TITLE", "Tips Karir SobatKerja")
            intent.putExtra("EXTRA_DESC", "Kumpulan artikel dan tips jitu untuk lolos wawancara kerja dengan mudah.")
            startActivity(intent)
        }

        // ==========================================
        // TOMBOL 4: Logout (AlertDialog & Snackbar)
        // ==========================================
        binding.btnLogout.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Konfirmasi Logout")
            builder.setMessage("Apakah Anda yakin ingin keluar dari aplikasi SobatKerja?")

            // Jika tombol "Ya" diklik
            builder.setPositiveButton("Ya") { dialog, which ->
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