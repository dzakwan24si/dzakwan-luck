package com.example.dzakwan_luck.Home

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.dzakwan_luck.databinding.ItemUserBinding

class UserAdapter(
    context: Context,
    private val users: List<UserModel>
) : ArrayAdapter<UserModel>(context, 0, users) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: ItemUserBinding
        var view = convertView

        if (view == null) {
            binding = ItemUserBinding.inflate(LayoutInflater.from(context), parent, false)
            view = binding.root
            view.tag = binding
        } else {
            binding = view.tag as ItemUserBinding
        }

        val user = users[position]

        binding.tvNamaUser.text = user.nama
        binding.tvEmailUser.text = user.email
        binding.tvRole.text = user.role

        /// 1. Berikan warna khusus untuk setiap Role (termasuk Admin)
        when (user.role.lowercase()) {
            "admin" -> binding.tvRole.setBackgroundColor(Color.parseColor("#2A4B9B")) // Navy Blue khas Bina Desa
            "kades" -> binding.tvRole.setBackgroundColor(Color.parseColor("#FFC107")) // Kuning
            else -> binding.tvRole.setBackgroundColor(Color.parseColor("#28A745")) // Hijau (Staff)
        }

        Glide.with(context)
            .load(user.avatar)  // Ubah dari user.avatarUrl menjadi user.avatar
            .circleCrop()
            .into(binding.ivAvatar)

        return view!!
    }
}