package com.example.dzakwan_luck

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dzakwan_luck.databinding.ActivityRegisterBinding
import java.util.Calendar

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Fitur DatePicker untuk Tanggal Lahir
        binding.etTanggalLahir.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(this, { _, year, monthOfYear, dayOfMonth ->
                val date = "$dayOfMonth/${monthOfYear + 1}/$year"
                binding.etTanggalLahir.setText(date)
            }, year, month, day).show()
        }

        // Action Tombol Selanjutnya
        binding.btnSelanjutnya.setOnClickListener {
            val nama = binding.etNama.text.toString()
            val email = binding.etUsernameReg.text.toString()
            val tglLahir = binding.etTanggalLahir.text.toString()
            val username = binding.etUsernameReg.text.toString()
            val password = binding.etPasswordReg.text.toString()
            val confirmPass = binding.etConfirmPassword.text.toString()

            val idJk = binding.rgJk.checkedRadioButtonId
            val jk = if (idJk == R.id.rb_laki) "Laki-laki" else if (idJk == R.id.rb_perempuan) "Perempuan" else ""

            // Simpan ke SharedPreferences
            val sp = getSharedPreferences("QuizB_Prefs", Context.MODE_PRIVATE)
            val editor = sp.edit()
            editor.putString("nama", nama)
            editor.putString("email", email)
            editor.putString("tglLahir", tglLahir)
            editor.putString("jk", jk)
            editor.putString("username", username)
            editor.putString("password", password)
            editor.putString("confirmPass", confirmPass)
            editor.apply()

            // Pindah ke halaman validasi
            startActivity(Intent(this, ValidationActivity::class.java))
        }
    }
}