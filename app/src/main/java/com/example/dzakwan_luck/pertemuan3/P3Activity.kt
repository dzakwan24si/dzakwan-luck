package com.example.dzakwan_luck.pertemuan3

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dzakwan_luck.MainActivity
import com.example.dzakwan_luck.databinding.ActivityP3Binding
import androidx.core.content.edit
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
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
            if (username == password) {
                sharedPref.edit {
                    putBoolean("isLogin", true)
                    putString("username", username)
                }
                Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

            } else {
                MaterialAlertDialogBuilder(this)
                    .setTitle("Login Gagal")
                    .setMessage("Silahkan coba lagi")
                    .setNegativeButton("Ok") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }
    }
}