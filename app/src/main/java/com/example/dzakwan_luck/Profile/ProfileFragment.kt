package com.example.dzakwan_luck.Profile

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.dzakwan_luck.R
import com.example.dzakwan_luck.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sharedPref = requireContext().getSharedPreferences("user_pref", MODE_PRIVATE)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Profil Pengembang"
        }
        // 1. Aksi klik untuk GitHub
        binding.btnGithub.setOnClickListener {
            bukaLink("https://github.com/dzakwan24si")
        }
        // 2. Aksi klik untuk Website
        binding.btnWebsite.setOnClickListener {
            bukaLink("https://dzakwan.vercel.app/")
        }
        // 3. Aksi klik untuk Instagram
        binding.btnInstagram.setOnClickListener {
            bukaLink("https://instagram.com/syafiqdzakwan")
        }
    }
    private fun bukaLink(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}