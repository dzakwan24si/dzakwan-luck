package com.example.dzakwan_luck.Home.pertemuan3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dzakwan_luck.MainActivity
import com.example.dzakwan_luck.databinding.ActivityP3Binding
import androidx.core.content.edit
import com.example.dzakwan_luck.BaseActivity
import com.example.dzakwan_luck.Home.HomeFragment
import com.example.dzakwan_luck.RegisterActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class P3Activity : AppCompatActivity() {

    private lateinit var binding: ActivityP3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Kode ini harus selalu dipanggil saat butuh akses "user_pref"
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        binding = ActivityP3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val inputUser = binding.etUsername.text.toString()
            val inputPass = binding.etPassword.text.toString()

            val sp = getSharedPreferences("QuizB_Prefs", Context.MODE_PRIVATE)
            val spUser = sp.getString("username", "")
            val spPass = sp.getString("password", "")

            // Kondisi 1: username == password (syarat praktikum sebelumnya)
            if (inputUser.isNotEmpty() && inputUser == inputPass) {
                Toast.makeText(this, "Login Praktikum Berhasil", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, BaseActivity::class.java))
                finish()
            }
            // Kondisi 2: username dan password cocok dengan di SP
            else if (inputUser.isNotEmpty() && inputUser == spUser && inputPass == spPass) {
                Toast.makeText(this, "Login Registrasi Berhasil", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, BaseActivity::class.java))
                finish()
            }
            else {
                MaterialAlertDialogBuilder(this)
                    .setTitle("Login Gagal")
                    .setMessage("Username atau password yang Anda masukkan salah. Silakan coba lagi.")
                    .setPositiveButton("Mengerti", null)
                    .show()
            }
        }

        binding.signUp.setOnClickListener { // Beri ID tv_sign_up di XML pada tulisan Sign Up
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}