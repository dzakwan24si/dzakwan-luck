package com.example.dzakwan_luck // Sesuaikan package

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.dzakwan_luck.Home.TutorialFragmentAdapter
import com.example.dzakwan_luck.Home.pertemuan3.P3Activity
import com.example.dzakwan_luck.Onboarding.Tutorial1Fragment
import com.example.dzakwan_luck.Onboarding.Tutorial2Fragment
import com.example.dzakwan_luck.Onboarding.Tutorial3Fragment
import com.example.dzakwan_luck.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. CEK SESI SEBELUM MENAMPILKAN UI
        val sp = getSharedPreferences("UserSession", MODE_PRIVATE)
        val isLoggedIn = sp.getBoolean("isLoggedIn", false)
        val hasSeenOnboarding = sp.getBoolean("hasSeenOnboarding", false)

        if (isLoggedIn) {
            // Kalau sudah login, langsung ke Dashboard/Home!
            startActivity(Intent(this, BaseActivity::class.java))
            finish()
            return // Hentikan eksekusi kode di bawahnya
        } else if (hasSeenOnboarding) {
            // Kalau belum login TAPI sudah pernah lihat Onboarding, langsung ke Login!
            startActivity(Intent(this, P3Activity::class.java))
            finish()
            return
        }


        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // --- Kalau belum dua-duanya, baru jalankan Onboarding di bawah ini ---
        val fragments = listOf(Tutorial1Fragment(), Tutorial2Fragment(), Tutorial3Fragment())
        val adapter = TutorialFragmentAdapter(this, fragments)
        binding.vpOnboarding.adapter = adapter
        binding.dotIndicator.attachTo(binding.vpOnboarding)

        binding.vpOnboarding.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position == fragments.size - 1) {
                    binding.btnAyoMulai.visibility = View.VISIBLE
                } else {
                    binding.btnAyoMulai.visibility = View.INVISIBLE
                }
            }
        })

        // 2. SIMPAN STATUS BAHWA USER SUDAH MELIHAT ONBOARDING
        binding.btnAyoMulai.setOnClickListener {
            val editor = sp.edit()
            editor.putBoolean("hasSeenOnboarding", true)
            editor.apply()

            // Pindah ke halaman Login
            val intent = Intent(this, P3Activity::class.java)
            startActivity(intent)
            finish()
        }
    }
}