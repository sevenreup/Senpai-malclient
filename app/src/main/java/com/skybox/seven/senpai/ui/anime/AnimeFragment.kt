package com.skybox.seven.senpai.ui.anime

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.skybox.seven.senpai.R
import com.skybox.seven.senpai.adapter.AnimeViewPagerAdapter
import com.skybox.seven.senpai.databinding.FragmentAnimeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_anime.*

@AndroidEntryPoint
class AnimeFragment : Fragment() {
    private var _binding: FragmentAnimeBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAnimeBinding.inflate(inflater, container, false)
        val view = binding.root

        val adapter = AnimeViewPagerAdapter(childFragmentManager, lifecycle)
        binding.viewpager.adapter = adapter
        binding.pageIndicator.setViewPager2(binding.viewpager)

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}