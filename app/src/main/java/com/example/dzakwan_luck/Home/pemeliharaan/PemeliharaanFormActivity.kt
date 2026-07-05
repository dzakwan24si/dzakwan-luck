package com.example.dzakwan_luck.Home.pemeliharaan

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.dzakwan_luck.BaseActivity
import com.example.dzakwan_luck.data.AppDatabase
import com.example.dzakwan_luck.data.entity.PemeliharaanEntity
import com.example.dzakwan_luck.databinding.ActivityPemeliharaanFormBinding
import com.example.dzakwan_luck.utils.PermissionHelper
import com.example.dzakwan_luck.utils.ReminderHelper
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class PemeliharaanFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPemeliharaanFormBinding
    private lateinit var db: AppDatabase

    // Variabel penampung untuk Mode Edit
    private var pemeliharaanId: Int = 0
    private var isEditMode = false
    private var existingImageUrl: String = "https://images.unsplash.com/photo-1581091226825-a6a2a5aee158?w=500" // Default gambar
    private var existingTanggal: String = ""

    // 1. Pendaftar Izin Notifikasi (Untuk Android 13+)
    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (!isGranted) {
                Toast.makeText(this, "Izin notifikasi ditolak. Pengingat tidak akan muncul.", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPemeliharaanFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)

        // 2. Minta Izin Notifikasi saat halaman form dibuka
        if (PermissionHelper.isNotificationPermissionRequired()) {
            val permission = Manifest.permission.POST_NOTIFICATIONS
            if (!PermissionHelper.hasPermission(this, permission)) {
                PermissionHelper.requestPermission(notificationPermissionLauncher, permission)
            }
        }

        // 3. Cek Mode Edit: Apakah ada ID yang dikirim dari Adapter?
        pemeliharaanId = intent.getIntExtra("EXTRA_ID", 0)
        if (pemeliharaanId != 0) {
            isEditMode = true

            // Ambil sisa data yang dikirim
            existingTanggal = intent.getStringExtra("EXTRA_TANGGAL") ?: ""
            existingImageUrl = intent.getStringExtra("EXTRA_IMAGE_URL") ?: existingImageUrl

            // Menghilangkan teks "Rp" dan titik agar mudah diedit ulang angkanya
            val biayaLamaRaw = intent.getStringExtra("EXTRA_BIAYA")?.replace("Rp ", "")?.replace(".", "") ?: ""

            // Isi form dengan data lama
            binding.etNamaAset.setText(intent.getStringExtra("EXTRA_NAMA_ASET"))
            binding.etKodeAset.setText(intent.getStringExtra("EXTRA_KODE_ASET"))
            binding.etTindakan.setText(intent.getStringExtra("EXTRA_TINDAKAN"))
            binding.etBiaya.setText(biayaLamaRaw)
            binding.cbAdaBukti.isChecked = intent.getBooleanExtra("EXTRA_ADA_BUKTI", false)

            binding.btnSimpanPemeliharaan.text = "Update Pemeliharaan"
            supportActionBar?.title = "Edit Pemeliharaan"
        } else {
            supportActionBar?.title = "Tambah Pemeliharaan"
        }

        binding.btnSimpanPemeliharaan.setOnClickListener {
            val nama = binding.etNamaAset.text.toString()
            val kode = binding.etKodeAset.text.toString()
            val tindakan = binding.etTindakan.text.toString()
            val biayaInput = binding.etBiaya.text.toString()
            val adaBukti = binding.cbAdaBukti.isChecked

            // Ambil data menit dari inputan baru
            val reminderMenitStr = binding.etReminderMenit.text.toString()

            // Tentukan tanggal (Gunakan tanggal lama jika mode edit, atau tanggal hari ini jika data baru)
            val tanggalFix = if (isEditMode) existingTanggal else SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())

            if (nama.isNotBlank() && tindakan.isNotBlank() && biayaInput.isNotBlank()) {

                var biayaRupiah = ""
                try {
                    val formatter = NumberFormat.getNumberInstance(Locale("in", "ID"))
                    val formattedNumber = formatter.format(biayaInput.toDouble())
                    biayaRupiah = "Rp $formattedNumber"
                } catch (e: Exception) {
                    biayaRupiah = "Rp $biayaInput"
                }

                lifecycleScope.launch {
                    val pemeliharaan = PemeliharaanEntity(
                        id = if (isEditMode) pemeliharaanId else 0, // Jangan lupa masukan ID jika mode edit
                        tanggal = tanggalFix,
                        namaAset = nama,
                        kodeAset = kode,
                        tindakan = tindakan,
                        biaya = biayaRupiah,
                        adaBukti = adaBukti,
                        imageUrl = existingImageUrl
                    )

                    // Eksekusi pembaruan atau penambahan data
                    if (isEditMode) {
                        db.pemeliharaanDao().updatePemeliharaan(pemeliharaan)
                    } else {
                        db.pemeliharaanDao().insertPemeliharaan(pemeliharaan)
                    }

                    // Menjalankan UI (Toast & Finish) di Main Thread
                    runOnUiThread {
                        // 4. LOGIKA ALARM PENGINGAT
                        if (reminderMenitStr.isNotBlank()) {
                            val menit = reminderMenitStr.toInt()
                            if (menit > 0) {
                                val calendar = Calendar.getInstance().apply {
                                    add(Calendar.MINUTE, menit) // Tambahkan menit ke waktu sekarang
                                }

                                ReminderHelper.setReminder(
                                    context = this@PemeliharaanFormActivity,
                                    hour = calendar.get(Calendar.HOUR_OF_DAY),
                                    minute = calendar.get(Calendar.MINUTE),
                                    title = "🛠️ Cek Pemeliharaan",
                                    message = "Waktunya mengecek hasil perbaikan untuk aset: $nama!",
                                    targetActivity = BaseActivity::class.java
                                )
                                Toast.makeText(this@PemeliharaanFormActivity, "Data tersimpan & Pengingat disetel untuk $menit menit lagi", Toast.LENGTH_LONG).show()
                            }
                        } else {
                            val pesanToast = if (isEditMode) "Data Pemeliharaan Diperbarui!" else "Data Pemeliharaan Tersimpan!"
                            Toast.makeText(this@PemeliharaanFormActivity, pesanToast, Toast.LENGTH_SHORT).show()
                        }

                        finish() // Tutup halaman
                    }
                }
            } else {
                Toast.makeText(this, "Harap lengkapi semua data wajib!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}