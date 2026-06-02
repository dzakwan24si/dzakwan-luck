package com.example.dzakwan_luck.Note

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.dzakwan_luck.data.AppDatabase
import com.example.dzakwan_luck.data.entity.NoteEntity
import com.example.dzakwan_luck.databinding.ActivityNoteFormBinding
import kotlinx.coroutines.launch

class NoteFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteFormBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi Database
        db = AppDatabase.getInstance(this)

        binding.btnSaveNote.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val content = binding.etContent.text.toString()

            if (title.isNotBlank() && content.isNotBlank()) {
                // Simpan ke Room Database
                lifecycleScope.launch {
                    val note = NoteEntity(
                        title = title,
                        content = content,
                        createdAt = System.currentTimeMillis()
                    )
                    db.noteDao().insertNote(note)
                    Toast.makeText(this@NoteFormActivity, "Catatan Disimpan!", Toast.LENGTH_SHORT).show()
                    finish() // Tutup halaman form
                }
            } else {
                Toast.makeText(this, "Judul dan isi tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}