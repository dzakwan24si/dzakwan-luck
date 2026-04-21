package com.example.dzakwan_luck.pertemuan4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dzakwan_luck.databinding.ActivityCustomOneBinding

class CustomOneActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCustomOneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomOneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Menangkap data dari Intent
        val titleFromIntent = intent.getStringExtra("EXTRA_TITLE")
        val descFromIntent = intent.getStringExtra("EXTRA_DESC")

        // 2. Memastikan data tidak kosong, lalu menampilkannya di TextView
        if (titleFromIntent != null && descFromIntent != null) {
            binding.tvCustomTitle.text = titleFromIntent
            binding.tvCustomDesc.text = descFromIntent
        }

        setSupportActionBar(binding.toolbarCustomOne)
        supportActionBar?.apply {
            title = "Detail Data"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}