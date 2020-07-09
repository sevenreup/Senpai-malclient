package com.skybox.seven.senpai.ui.anime

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.skybox.seven.senpai.adapter.AnimeViewPagerAdapter
import com.skybox.seven.senpai.databinding.FragmentAnimeBinding
import com.skybox.seven.senpai.epoxy.AnimeTabController
import com.skybox.seven.senpai.util.DefaultItemDecorator
import com.skybox.seven.senpai.util.ViewPagerTabsHandler
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnimeFragment : Fragment() {
    private var _binding: FragmentAnimeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AnimeViewModel by activityViewModels()
    private val args: AnimeFragmentArgs by navArgs()

    private lateinit var tabsHandler: ViewPagerTabsHandler;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAnimeBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel.activeAnimeData.value = args.anime

        val adapter = AnimeViewPagerAdapter(childFragmentManager, lifecycle)
        val controller = AnimeTabController { binding.animeViewpager.setCurrentItem(it, true)}
        binding.animeViewpager.adapter = adapter
        binding.tabsRecycler.setController(controller)
        binding.tabsRecycler.setItemSpacingPx(100)
        binding.tabsRecycler.addItemDecoration(DefaultItemDecorator(20, 20))
        controller.setData(AnimeViewPagerAdapter.animeTabTitles)

        tabsHandler = ViewPagerTabsHandler(binding.animeViewpager, binding.tabsRecycler)
        tabsHandler.init()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val anime = args.anime
        Glide.with(requireContext()).load(anime.imageUrl).into(binding.animeCover)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val numOfTabs = 2
    }
}