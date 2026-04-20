package com.example.dzakwan_luck

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.dzakwan_luck.databinding.ActivityMainBinding
import com.example.dzakwan_luck.databinding.ActivitySplashScreenBinding
import com.example.dzakwan_luck.pertemuan3.P3Activity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Akses SharedPreferences
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        val isLogin = sharedPref.getBoolean("isLogin", false)

        lifecycleScope.launch {
            delay(2000)
            if (isLogin) {
                // Jika sudah login, langsung ke Dashboard (MainActivity)
                startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
            } else {
                // Jika belum, arahkan ke Login (AuthActivity)
                startActivity(Intent(this@SplashScreenActivity, P3Activity::class.java))
            }
            finish()
        }
    }
}