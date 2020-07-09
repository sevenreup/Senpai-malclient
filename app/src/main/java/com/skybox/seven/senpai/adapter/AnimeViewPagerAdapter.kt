package com.skybox.seven.senpai.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.skybox.seven.senpai.ui.anime.animeTabs.AnimeDescriptionFragment
import com.skybox.seven.senpai.ui.anime.animeTabs.AnimeMediaFragment

class AnimeViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        when(position) {
            1-> return AnimeMediaFragment()
        }
        return AnimeDescriptionFragment()
    }

    companion object {
        val animeTabTitles = arrayListOf("First", "Second")
    }
}