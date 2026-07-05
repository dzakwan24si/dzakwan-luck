package com.example.dzakwan_luck

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
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        // SIMPAN STATUS BAHWA USER SUDAH MELIHAT ONBOARDING
        binding.btnAyoMulai.setOnClickListener {
            val sp = getSharedPreferences("UserSession", MODE_PRIVATE)
            val editor = sp.edit()
            editor.putBoolean("hasSeenOnboarding", true)
            editor.apply()

            // Pindah ke halaman Login (P3Activity)
            val intent = Intent(this, P3Activity::class.java)
            startActivity(intent)
            finish()
        }
    }
}