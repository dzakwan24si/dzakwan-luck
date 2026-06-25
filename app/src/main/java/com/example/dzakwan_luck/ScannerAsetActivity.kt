package com.example.dzakwan_luck

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dzakwan_luck.databinding.ActivityScannerAsetBinding
import com.google.android.material.tabs.TabLayoutMediator

class ScannerAsetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScannerAsetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScannerAsetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Toolbar Back Button
        binding.toolbar.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        // Setup ViewPager dan Adapter
        val adapter = ScannerTabsAdapter(this)
        binding.viewPager.adapter = adapter

        // Hubungkan TabLayout dengan ViewPager
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Foto Aset"
                1 -> "Cetak QR"
                2 -> "Scan Aset"
                else -> ""
            }
        }.attach()
    }
}