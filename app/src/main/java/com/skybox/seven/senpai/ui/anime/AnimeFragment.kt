package com.skybox.seven.senpai.ui.anime

import android.os.Bundle
import android.view.DragEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.skybox.seven.senpai.R
import com.skybox.seven.senpai.adapter.AnimeViewPagerAdapter
import com.skybox.seven.senpai.databinding.FragmentAnimeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_anime.*

@AndroidEntryPoint
class AnimeFragment : Fragment() {
    private var _binding: FragmentAnimeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AnimeViewModel by activityViewModels()
    private val args: AnimeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAnimeBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel.activeAnimeData.value = args.anime

        val adapter = AnimeViewPagerAdapter(childFragmentManager, lifecycle)
        binding.viewpager.adapter = adapter
        binding.pageIndicator.setViewPager2(binding.viewpager)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val anime = args.anime
        binding.animeTitle.text = anime.title
        Glide.with(view).load(anime.imageUrl).into(binding.animeCover)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}