package com.example.dzakwan_luck.Home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class InventarisTabsAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3 // 3 Menu: Data Aset, Pemeliharaan, Mutasi

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TabDaftarAsetFragment() // Tab ke-1 berisi RecyclerView
            1 -> TabPemeliharaanFragment() // Tab ke-2
            2 -> TabMutasiFragment() // Tab ke-3
            else -> throw IllegalStateException("Posisi tidak valid")
        }
    }
}