package com.example.dzakwan_luck

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dzakwan_luck.Home.pertemuan3.P3Activity
import com.example.dzakwan_luck.databinding.ActivityValidationBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ValidationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityValidationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityValidationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ambil data dari SP
        val sp = getSharedPreferences("QuizB_Prefs", Context.MODE_PRIVATE)
        val nama = sp.getString("nama", "")
        val email = sp.getString("email", "")
        val tglLahir = sp.getString("tglLahir", "")
        val jk = sp.getString("jk", "")
        val username = sp.getString("username", "")
        val password = sp.getString("password", "")
        val confirmPass = sp.getString("confirmPass", "")

        // Tampilkan data di UI
        binding.tvRingkasanData.text = """
            Nama: $nama
            Email: $email
            Tanggal Lahir: $tglLahir
            Jenis Kelamin: $jk
            Username: $username
        """.trimIndent()

        binding.btnSubmitValidasi.setOnClickListener {
            // Syarat 1: Tidak boleh kosong
            if (nama!!.isEmpty() || email!!.isEmpty() || tglLahir!!.isEmpty() || jk!!.isEmpty() || username!!.isEmpty() || password!!.isEmpty()) {
                tampilkanError("Terdapat isian yang kosong! Silakan perbaiki.")
            }
            // Syarat 2: Password = Confirm Password
            else if (password != confirmPass) {
                tampilkanError("Password dan Confirm Password tidak cocok!")
            }
            // Jika Lolos Validasi
            else {
                MaterialAlertDialogBuilder(this)
                    .setTitle("Registrasi Berhasil")
                    .setMessage("Data Anda telah tervalidasi dan berhasil disimpan.")
                    .setCancelable(false)
                    .setPositiveButton("Ke Halaman Login") { _, _ ->
                        // Pindah ke halaman Login (P3Activity)
                        val intent = Intent(this, P3Activity::class.java)
                        // Membersihkan tumpukan halaman agar tidak bisa di-back
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    }
                    .show()
            }
        }

        // Tombol Kembali
        binding.btnKembaliPerbaiki.setOnClickListener {
            finish() // Menutup halaman validasi, otomatis kembali ke form registrasi
        }
    }

    // Fungsi bantuan untuk menampilkan error & memunculkan tombol kembali
    private fun tampilkanError(pesan: String) {
        binding.tvErrorMsg.text = pesan
        binding.tvErrorMsg.visibility = View.VISIBLE
        binding.btnKembaliPerbaiki.visibility = View.VISIBLE
    }
}