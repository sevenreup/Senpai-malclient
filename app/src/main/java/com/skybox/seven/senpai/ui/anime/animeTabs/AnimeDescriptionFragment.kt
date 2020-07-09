package com.skybox.seven.senpai.ui.anime.animeTabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.google.android.material.chip.Chip
import com.skybox.seven.senpai.api.jikan.model.Anime
import com.skybox.seven.senpai.databinding.FragmentAnimeBinding
import com.skybox.seven.senpai.databinding.FragmentAnimeDescriptionBinding
import com.skybox.seven.senpai.ui.anime.AnimeViewModel


class AnimeDescriptionFragment : Fragment() {
    private var _binding: FragmentAnimeDescriptionBinding? = null
    private val binding get() = _binding!!


    private val viewModel: AnimeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAnimeDescriptionBinding.inflate(inflater, container, false)
        val v = binding.root

        viewModel.activeAnimeData.observe(viewLifecycleOwner, Observer  {
            binding.synopsis.text = it.synopsis
            createGenreChips(it)
        })

        return v;
    }

    private fun createGenreChips(anime: Anime) {
        anime.genres.forEach {
            val chip = Chip(context)
            chip.text = it.name
            binding.genreChips.addView(chip)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}