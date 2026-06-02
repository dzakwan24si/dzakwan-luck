package com.example.dzakwan_luck.Note

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dzakwan_luck.data.AppDatabase
import com.example.dzakwan_luck.data.entity.NoteEntity
import com.example.dzakwan_luck.databinding.FragmentNoteBinding
import kotlinx.coroutines.launch

class FragmentNote : Fragment() {
    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: NoteAdapter
    private lateinit var db: AppDatabase
    private val notesList = mutableListOf<NoteEntity>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi
        db = AppDatabase.getInstance(requireContext())
        adapter = NoteAdapter(notesList, this)

        binding.rvNotes.layoutManager = LinearLayoutManager(requireContext())
        binding.rvNotes.adapter = adapter

        // Buka halaman Form saat FAB diklik
        binding.fabAddNote.setOnClickListener {
            startActivity(Intent(requireContext(), NoteFormActivity::class.java))
        }
    }

    // Fungsi mengambil data dari Room
    private fun fetchNotes() {
        lifecycleScope.launch {
            val data = db.noteDao().getAllNotes()
            notesList.clear()
            notesList.addAll(data)
            adapter.notifyDataSetChanged()
        }
    }

    // Fungsi menghapus data (Dipanggil oleh Adapter)
    fun deleteNote(note: NoteEntity) {
        lifecycleScope.launch {
            db.noteDao().deleteNote(note)
            fetchNotes() // Refresh layar setelah dihapus
        }
    }

    // Memuat ulang data setiap kali kembali ke halaman ini
    override fun onResume() {
        super.onResume()
        fetchNotes()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}