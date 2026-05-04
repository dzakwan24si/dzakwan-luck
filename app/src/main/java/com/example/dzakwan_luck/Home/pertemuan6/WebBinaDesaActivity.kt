package com.example.dzakwan_luck.Home.pertemuan6

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dzakwan_luck.R
import com.example.dzakwan_luck.databinding.ActivityWebBinaDesaBinding

class WebBinaDesaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebBinaDesaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityWebBinaDesaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. SETUP TOOLBAR & MUNCULKAN TOMBOL BACK (PANAH)
        setSupportActionBar(binding.toolbarWeb)
        supportActionBar?.apply {
            title = "Web View Bina Desa"
            setDisplayHomeAsUpEnabled(true) // Memunculkan ikon panah kembali
            setDisplayShowHomeEnabled(true)
        }

        // 2. SETUP WEBVIEW
        binding.webViewBinaDesa.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            loadUrl("https://dzakwan-dev.alwaysdata.net/inventaris-admin/login")
        }
    }
    // 3. FUNGSI AGAR TOMBOL PANAH BACK DI TOOLBAR BISA DIKLIK
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed() // Perintah untuk menutup halaman ini (kembali ke Dashboard)
        return true
    }
}