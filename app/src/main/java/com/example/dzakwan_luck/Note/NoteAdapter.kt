package com.example.dzakwan_luck.Note

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dzakwan_luck.data.entity.NoteEntity
import com.example.dzakwan_luck.databinding.ItemNoteBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class NoteAdapter(
    private val notes: List<NoteEntity>, // Menggunakan parameter notes
    private val fragment: FragmentNote
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position] // Perbaikan: menggunakan 'notes' yang benar

        holder.binding.tvTitle.text = note.title
        holder.binding.tvContent.text = note.content

        // Tambahkan aksi klik untuk Edit
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, NoteFormActivity::class.java).apply {
                putExtra("EXTRA_ID", note.id)
                putExtra("EXTRA_TITLE", note.title)
                putExtra("EXTRA_CONTENT", note.content) // Perbaikan: menggunakan 'content' sesuai NoteEntity
            }
            holder.itemView.context.startActivity(intent)
        }

        // Aksi Tombol Hapus
        holder.binding.btnDelete.setOnClickListener {
            MaterialAlertDialogBuilder(holder.itemView.context)
                .setTitle("Hapus Catatan")
                .setMessage("Apakah Anda yakin ingin menghapus catatan ini?")
                .setPositiveButton("Hapus") { dialog, _ ->
                    fragment.deleteNote(note)
                    dialog.dismiss()
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    override fun getItemCount(): Int = notes.size
}