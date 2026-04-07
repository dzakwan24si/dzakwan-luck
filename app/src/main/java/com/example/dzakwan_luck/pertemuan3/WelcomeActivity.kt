package com.example.dzakwan_luck.pertemuan3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
// Import ViewBinding untuk layout Welcome
import com.example.dzakwan_luck.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}