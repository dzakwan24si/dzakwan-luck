package com.example.dzakwan_luck.pertemuan4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dzakwan_luck.databinding.ActivityCustomTwoBinding

class CustomTwoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCustomTwoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Menangkap dan memasang data dari Intent Extra
        val titleFromIntent = intent.getStringExtra("EXTRA_TITLE")
        val descFromIntent = intent.getStringExtra("EXTRA_DESC")

        if (titleFromIntent != null && descFromIntent != null) {
            binding.tvCustomTitle.text = titleFromIntent
            binding.tvCustomDesc.text = descFromIntent
        }

        // Aksi untuk tombol Continue (bisa dikosongkan atau diberi aksi kembali)
        binding.btnContinue.setOnClickListener {
            finish()
        }
    }
}