package com.example.dzakwan_luck.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager // Import ini
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dzakwan_luck.databinding.FragmentTabPemeliharaanBinding

class TabPemeliharaanFragment : Fragment() {
    private var _binding: FragmentTabPemeliharaanBinding? = null
    private val binding get() = _binding!!

    // Data dari screenshot web disesuaikan dengan foto barang
    private val listData = listOf(
        PemeliharaanModel("16/04/2026", "Mobil Avanza Dinas #44", "AST-044", "Ipsam perferendis ex laborum.", "Rp 1.890.419", true, "https://loremflickr.com/400/300/car,vehicle/all?lock=44"),
        PemeliharaanModel("13/04/2026", "Proyektor Sony #16", "AST-016", "Vitae voluptas quasi omnis.", "Rp 1.646.870", false, "https://loremflickr.com/400/300/projector,electronics/all?lock=16"),
        PemeliharaanModel("12/04/2026", "Server Rakitan #5", "AST-005", "Dolor sapiente velit atque occaecati per...", "Rp 1.263.033", true, "https://loremflickr.com/400/300/server,rack/all?lock=5"),
        PemeliharaanModel("10/04/2026", "Laptop Asus ROG #31", "AST-031", "Suscipit enim sed quasi voluptatem ut di...", "Rp 652.527", false, "https://loremflickr.com/400/300/laptop,computer/all?lock=31"),
        PemeliharaanModel("10/04/2026", "Proyektor Sony #49", "AST-049", "Dolores quo dolorem voluptatem similique...", "Rp 655.825", false, "https://loremflickr.com/400/300/projector,meeting/all?lock=49"),
        PemeliharaanModel("08/04/2026", "Brankas Uang #26", "AST-026", "Enim sequi qui ad qui illo consequatur.", "Rp 581.453", true, "https://loremflickr.com/400/300/safe,vault/all?lock=26"),
        PemeliharaanModel("06/04/2026", "AC Panasonic 2PK #21", "AST-021", "Soluta tempora cumque velit.", "Rp 393.358", false, "https://loremflickr.com/400/300/ac,airconditioner/all?lock=21")
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTabPemeliharaanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Ubah ListData menjadi listData (huruf kecil)
        val adapter = PemeliharaanAdapter(listData) { }

        binding.rvPemeliharaan.apply {
            // 2. Gunakan LinearLayoutManager karena desainnya list ke bawah
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = adapter
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}