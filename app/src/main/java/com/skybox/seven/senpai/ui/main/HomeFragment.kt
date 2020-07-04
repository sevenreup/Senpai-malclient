package com.skybox.seven.senpai.ui.main

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.airbnb.epoxy.EpoxyRecyclerView
import com.airbnb.epoxy.addGlidePreloader
import com.airbnb.epoxy.glidePreloader
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import com.skybox.seven.senpai.R
import com.skybox.seven.senpai.epoxy.anime.AnimeOneModel_
import com.skybox.seven.senpai.epoxy.home.HomeController
import dagger.hilt.android.AndroidEntryPoint
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
        val homeController = HomeController(context)
        recycler.setController(homeController)
        recycler.addGlidePreloader(Glide.with(context!!), preloader =
        glidePreloader { requestManager, epoxyModel: AnimeOneModel_, _->
            requestManager.loadImage(epoxyModel.image(), true)
        })

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

fun RequestManager.loadImage(url: String, isPreloading: Boolean): RequestBuilder<Bitmap> {

    val options = RequestOptions
        .diskCacheStrategyOf(DiskCacheStrategy.AUTOMATIC)
        .dontAnimate()
        .signature(ObjectKey(url.plus(if (isPreloading) "_preloading" else "_not_preloading")))

    return asBitmap()
        .apply(options)
        .load(url)
}