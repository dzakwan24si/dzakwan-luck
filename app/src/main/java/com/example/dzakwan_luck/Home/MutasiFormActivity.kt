package com.example.dzakwan_luck.Home

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dzakwan_luck.R
import com.google.android.material.textfield.TextInputEditText

class MutasiFormActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mutasi_form)

        supportActionBar?.hide() // Sembunyikan action bar bawaan karena kita pakai custom toolbar

        val btnSimpan = findViewById<Button>(R.id.btnSimpanMutasi)
        val etNamaAset = findViewById<TextInputEditText>(R.id.etNamaAsetMutasi)

        btnSimpan.setOnClickListener {
            val nama = etNamaAset.text.toString()
            if (nama.isNotEmpty()) {
                // Dummy Action untuk keperluan Demo UAS
                Toast.makeText(this, "Data Mutasi '$nama' Berhasil Disimpan!", Toast.LENGTH_SHORT).show()
                finish() // Menutup halaman dan kembali ke daftar
            } else {
                Toast.makeText(this, "Nama Aset tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}