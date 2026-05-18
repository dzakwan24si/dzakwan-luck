package com.example.dzakwan_luck.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dzakwan_luck.databinding.FragmentInventarisBinding
import com.google.android.material.tabs.TabLayoutMediator

class InventarisFragment : Fragment() {
    private var _binding: FragmentInventarisBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentInventarisBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Tombol Back Toolbar
        binding.toolbarInventaris.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        // Hubungkan Adapter ke ViewPager
        val tabsAdapter = InventarisTabsAdapter(this)
        binding.viewPagerInventaris.adapter = tabsAdapter

        // Hubungkan TabLayout dengan ViewPager
        TabLayoutMediator(binding.tabLayoutInventaris, binding.viewPagerInventaris) { tab, position ->
            when (position) {
                0 -> tab.text = "Data Aset"
                1 -> tab.text = "Pemeliharaan"
                2 -> tab.text = "Mutasi"
            }
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}