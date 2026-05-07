package com.example.dzakwan_luck.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.dzakwan_luck.R
import com.example.dzakwan_luck.databinding.FragmentManajemenUserBinding // Sesuaikan package

class ManajemenUserFragment : Fragment() {

    private var _binding: FragmentManajemenUserBinding? = null
    private val binding get() = _binding!!

    // Data simulasi sesuai dengan web Bina Desa
    private val userList = listOf(
        UserModel("M. Dzakwan Syafiq", "dzakwan@mahasiswa.pcr.ac.id", "Admin", R.drawable.my_foto_removebg_preview),
        UserModel("Irfan Nashiruddin", "febi56@example.com", "Staff", "https://i.pravatar.cc/150?img=3"),
        UserModel("Darman Rajasa", "luthfi.nasyiah@example.com", "Staff", "https://i.pravatar.cc/150?img=67"),
        UserModel("Kanda Simanjuntak", "hairyanto@example.com", "Kades", "https://i.pravatar.cc/150?img=65"),
        UserModel("Garang Zulkarnain", "restu@example.org", "Staff", "https://i.pravatar.cc/150?img=64"),
        UserModel("Mila Lestari", "lala90@example.org", "Kades", "https://i.pravatar.cc/150?img=5")
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentManajemenUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Pasang Adapter ke ListView
        val adapter = UserAdapter(requireContext(), userList)
        binding.listViewUsers.adapter = adapter

        // Aksi saat item diklik
        binding.listViewUsers.setOnItemClickListener { _, _, position, _ ->
            val clickedUser = userList[position]
            Toast.makeText(requireContext(), "Role ${clickedUser.nama} adalah ${clickedUser.role}", Toast.LENGTH_SHORT).show()
        }

        binding.toolbarUser .setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}