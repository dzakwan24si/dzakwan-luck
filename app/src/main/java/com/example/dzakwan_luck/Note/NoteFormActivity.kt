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
    private var noteId: Int = 0
    private var isEditMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi Database
        db = AppDatabase.getInstance(this)

        // 1. Cek Mode Edit
        noteId = intent.getIntExtra("EXTRA_ID", 0)
        if (noteId != 0) {
            isEditMode = true

            // Perbaikan: ID disamakan dengan etTitle dan etContent
            binding.etTitle.setText(intent.getStringExtra("EXTRA_TITLE"))
            binding.etContent.setText(intent.getStringExtra("EXTRA_CONTENT"))

            binding.btnSaveNote.text = "Update Catatan"
            supportActionBar?.title = "Edit Catatan"
        } else {
            supportActionBar?.title = "Tambah Catatan"
        }

        binding.btnSaveNote.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val content = binding.etContent.text.toString()

            if (title.isNotBlank() && content.isNotBlank()) {
                lifecycleScope.launch {
                    // Perbaikan: Memisahkan logika Update dan Insert
                    if (isEditMode) {
                        val note = NoteEntity(
                            id = noteId, // Wajib mengirimkan ID lama agar data tertimpa
                            title = title,
                            content = content,
                            createdAt = System.currentTimeMillis()
                        )
                        db.noteDao().updateNote(note)

                        runOnUiThread {
                            Toast.makeText(this@NoteFormActivity, "Catatan Diperbarui!", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                    } else {
                        val note = NoteEntity(
                            title = title,
                            content = content,
                            createdAt = System.currentTimeMillis()
                        )
                        db.noteDao().insertNote(note)

                        runOnUiThread {
                            Toast.makeText(this@NoteFormActivity, "Catatan Disimpan!", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                    }
                }
            } else {
                Toast.makeText(this, "Judul dan isi tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}