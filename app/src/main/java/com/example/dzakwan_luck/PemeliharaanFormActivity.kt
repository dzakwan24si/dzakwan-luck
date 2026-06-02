package com.example.dzakwan_luck

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.dzakwan_luck.data.AppDatabase
import com.example.dzakwan_luck.data.entity.PemeliharaanEntity
import com.example.dzakwan_luck.databinding.ActivityPemeliharaanFormBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.text.NumberFormat

class PemeliharaanFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPemeliharaanFormBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPemeliharaanFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)

        binding.btnSimpanPemeliharaan.setOnClickListener {
            val nama = binding.etNamaAset.text.toString()
            val kode = binding.etKodeAset.text.toString()
            val tindakan = binding.etTindakan.text.toString()
            val biayaInput = binding.etBiaya.text.toString() // Ambil angka mentahnya dulu
            val adaBukti = binding.cbAdaBukti.isChecked

            val tanggalHariIni = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())

            if (nama.isNotBlank() && tindakan.isNotBlank() && biayaInput.isNotBlank()) {

                // --- KODE FORMAT RUPIAH ---
                var biayaRupiah = ""
                try {
                    // Gunakan standar format angka Indonesia (ID)
                    val formatter = NumberFormat.getNumberInstance(Locale("in", "ID"))
                    val formattedNumber = formatter.format(biayaInput.toDouble())
                    biayaRupiah = "Rp $formattedNumber"
                } catch (e: Exception) {
                    biayaRupiah = "Rp $biayaInput"
                }
                // --------------------------

                lifecycleScope.launch {
                    val pemeliharaan = PemeliharaanEntity(
                        tanggal = tanggalHariIni,
                        namaAset = nama,
                        kodeAset = kode,
                        tindakan = tindakan,
                        biaya = biayaRupiah, // Masukkan variabel yang sudah diformat
                        adaBukti = adaBukti,
                        imageUrl = "https://images.unsplash.com/photo-1581091226825-a6a2a5aee158?w=500"
                    )
                    db.pemeliharaanDao().insertPemeliharaan(pemeliharaan)
                    Toast.makeText(this@PemeliharaanFormActivity, "Data Pemeliharaan Tersimpan!", Toast.LENGTH_SHORT).show()
                    finish()
                }
            } else {
                Toast.makeText(this, "Harap lengkapi semua data!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}