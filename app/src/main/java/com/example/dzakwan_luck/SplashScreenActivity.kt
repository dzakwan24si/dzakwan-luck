package com.example.dzakwan_luck

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.dzakwan_luck.Home.pertemuan3.P3Activity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        // Delay selama 2 detik
        Handler(Looper.getMainLooper()).postDelayed({

            // Panggil memori UserSession milikmu
            val sp = getSharedPreferences("UserSession", Context.MODE_PRIVATE)
            val isLoggedIn = sp.getBoolean("isLoggedIn", false)
            val hasSeenOnboarding = sp.getBoolean("hasSeenOnboarding", false)

            val intent = when {
                isLoggedIn -> {
                    // Skenario 1: Sudah login -> Ke Dashboard
                    Intent(this, BaseActivity::class.java)
                }
                hasSeenOnboarding -> {
                    // Skenario 2: Belum login tapi sudah pernah lihat onboarding -> Ke Login (P3Activity)
                    Intent(this, P3Activity::class.java)
                }
                else -> {
                    // Skenario 3: Baru pertama kali install -> Ke Onboarding
                    Intent(this, OnboardingActivity::class.java)
                }
            }

            startActivity(intent)
            finish() // Tutup SplashScreen

        }, 2000)
    }
}