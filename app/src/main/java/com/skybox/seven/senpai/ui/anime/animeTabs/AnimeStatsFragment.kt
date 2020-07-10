package com.skybox.seven.senpai.ui.anime.animeTabs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.skybox.seven.senpai.databinding.FragmentAnimeStatsBinding
import com.skybox.seven.senpai.ui.anime.AnimeViewModel

private const val TAG = "AnimeStatsFragment"
class AnimeStatsFragment : Fragment() {
    private var _binding: FragmentAnimeStatsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AnimeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAnimeStatsBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel.animeStatsData.observe(viewLifecycleOwner, Observer {
            Log.e(TAG, "onCreateView: ")
        })

        viewModel.getStats(viewModel.activeAnimeData.value?.malId)
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}