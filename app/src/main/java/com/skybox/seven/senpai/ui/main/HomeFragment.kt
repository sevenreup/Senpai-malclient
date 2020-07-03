package com.skybox.seven.senpai.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.skybox.seven.senpai.R
import dagger.hilt.android.AndroidEntryPoint
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_home.*

private const val TAG = "HomeFragment"
@AndroidEntryPoint
class HomeFragment : Fragment() {
    lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_home, container, false)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        viewModel.randomSpotAnime.observe(viewLifecycleOwner, Observer {
            spot_anime_title.text = it.title
            spot_anime_information.text = it.score.toString()
        })
        
        viewModel.getRandomSeason()
        return v
    }

}