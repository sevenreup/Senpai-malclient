package com.skybox.seven.senpai.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.skybox.seven.senpai.R
import dagger.hilt.android.AndroidEntryPoint
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.epoxy.EpoxyRecyclerView
import com.bumptech.glide.Glide
import com.skybox.seven.senpai.epoxy.PlaceHolder
import com.skybox.seven.senpai.epoxy.home.CarouselOneHomeModel
import com.skybox.seven.senpai.epoxy.home.HomeController
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
        val recycler = v.findViewById<EpoxyRecyclerView>(R.id.main_recyclerview)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val homeController = PlaceHolder(context)
        recycler.setController(homeController)

        viewModel.randomSpotAnime.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                Log.e(TAG, "onCreateView: ${it.score}")
                spot_anime_title.text = it.title
                spot_anime_information.text = it.score.toString()
                Glide.with(this)
                    .load(it.imageUrl)
                    .centerCrop()
                    .into(spot_anime_image)
            }

        })

        viewModel.trendingAnime.observe(viewLifecycleOwner, Observer {
            Log.e(TAG, "onCreateView: $it")
            homeController.setData(false, it)
        })
        
        viewModel.getRandomSeason()
        return v
    }

}