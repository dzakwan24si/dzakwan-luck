package com.example.dzakwan_luck.pertemuan3

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dzakwan_luck.MainActivity
import com.example.dzakwan_luck.databinding.ActivityP3Binding
import androidx.core.content.edit

class P3Activity : AppCompatActivity() {

    private lateinit var binding: ActivityP3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityP3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
            sharedPref.edit {
                putBoolean("isLogin", true)
                putString("username", binding.etUsername.text.toString())
            }
            // Eksekusi penyimpanan
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}