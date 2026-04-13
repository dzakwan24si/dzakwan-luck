package com.example.dzakwan_luck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class P2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_p2)

        // --- DEKLARASI KOMPONEN SEGITIGA ---
        val etAlas = findViewById<EditText>(R.id.et_alas)
        val etTinggiSegitiga = findViewById<EditText>(R.id.et_tinggi_segitiga)
        val btnHitungSegitiga = findViewById<Button>(R.id.btn_hitung_segitiga)
        val tvHasilSegitiga = findViewById<TextView>(R.id.tv_hasil_segitiga)

        // --- DEKLARASI KOMPONEN BALOK ---
        val etPanjang = findViewById<EditText>(R.id.et_panjang)
        val etLebar = findViewById<EditText>(R.id.et_lebar)
        val etTinggiBalok = findViewById<EditText>(R.id.et_tinggi_balok)
        val btnHitungBalok = findViewById<Button>(R.id.btn_hitung_balok)
        val tvHasilBalok = findViewById<TextView>(R.id.tv_hasil_balok)

        // --- LOGIKA HITUNG SEGITIGA ---
        btnHitungSegitiga.setOnClickListener {
            val alasStr = etAlas.text.toString()
            val tinggiStr = etTinggiSegitiga.text.toString()

            if (alasStr.isNotEmpty() && tinggiStr.isNotEmpty()) {
                val alas = alasStr.toDouble()
                val tinggi = tinggiStr.toDouble()
                val luas = 0.5 * alas * tinggi

                tvHasilSegitiga.text = "Hasil: $luas"

                // Menampilkan log di Logcat
                Log.d("DzakwanApp", "Menghitung Luas Segitiga: Alas=$alas, Tinggi=$tinggi, Hasil=$luas")
            } else {
                Toast.makeText(this, "Alas dan Tinggi tidak boleh kosong!", Toast.LENGTH_SHORT).show()
                Log.w("DzakwanApp", "Gagal hitung segitiga: Input kosong")
            }
        }

        // --- LOGIKA HITUNG BALOK ---
        btnHitungBalok.setOnClickListener {
            val pStr = etPanjang.text.toString()
            val lStr = etLebar.text.toString()
            val tStr = etTinggiBalok.text.toString()

            if (pStr.isNotEmpty() && lStr.isNotEmpty() && tStr.isNotEmpty()) {
                val p = pStr.toDouble()
                val l = lStr.toDouble()
                val t = tStr.toDouble()
                val volume = p * l * t

                tvHasilBalok.text = "Hasil: $volume"

                // Menampilkan log di Logcat
                Log.d("DzakwanApp", "Menghitung Vol Balok: P=$p, L=$l, T=$t, Hasil=$volume")
            } else {
                Toast.makeText(this, "Panjang, Lebar, dan Tinggi tidak boleh kosong!", Toast.LENGTH_SHORT).show()
                Log.w("DzakwanApp", "Gagal hitung balok: Input kosong")
            }
        }
    }
}