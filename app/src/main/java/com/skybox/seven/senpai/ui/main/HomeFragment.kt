package com.skybox.seven.senpai.ui.main

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
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
import com.skybox.seven.senpai.api.jikan.model.Anime
import com.skybox.seven.senpai.epoxy.anime.AnimeOneModel_
import com.skybox.seven.senpai.epoxy.anime.AnimeTwoModel_
import com.skybox.seven.senpai.epoxy.home.HomeController
import com.skybox.seven.senpai.ui.anime.AnimeFragmentDirections
import com.skybox.seven.senpai.ui.home.HomeSharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.fragment_home.*

private const val TAG = "HomeFragment"

@AndroidEntryPoint
class HomeFragment : Fragment(), HomeController.BasicControllerCallbacks {
    val viewModel: HomeViewModel by activityViewModels()
    val sharedViewModel: HomeSharedViewModel by activityViewModels()
    private var v: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (v==null) {
            v = inflater.inflate(R.layout.fragment_home, container, false)
            val recycler = v?.findViewById<EpoxyRecyclerView>(R.id.main_recyclerview)
            val homeController = HomeController(context, this)
            recycler?.setController(homeController)
            recycler?.addGlidePreloader(Glide.with(requireContext()), preloader =
            glidePreloader { requestManager, epoxyModel: AnimeOneModel_, _ ->
                requestManager.loadImage(epoxyModel.image(), true, epoxyModel.holderType)
            })
            recycler?.addGlidePreloader(Glide.with(requireContext()), preloader =
            glidePreloader { requestManager: RequestManager, epoxyModel: AnimeTwoModel_, _ ->
                requestManager.loadImage(epoxyModel.image(), true, epoxyModel.holderType)
            })

            recycler?.addOnScrollListener(object: RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    sharedViewModel.scrollDirection.value = dy
                }
            })
            viewModel.randomSpotAnime.observe(viewLifecycleOwner, Observer {
                if (it != null) {
                    Log.e(TAG, "onCreateView: ${it.score}")
                    spot_anime_title.text = it.title
                    spot_anime_information.text = if (it.score == null) "Not scored" else it.score.toString()
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
        }
        return v
    }

    override fun onAnimeClick(anime: Anime) {
        val action = AnimeFragmentDirections.toAnimePage(anime)
        findNavController().navigate(action)
    }

}

fun RequestManager.loadImage(
    url: String,
    isPreloading: Boolean,
    holderType: Int
): RequestBuilder<Bitmap> {

    val options = provideGenericRequestOptions(url, isPreloading)
    when (holderType) {
        0 -> {
            options.dontAnimate()
        }
        1 -> {
            options.transform(
                RoundedCornersTransformation(
                    40,
                    0,
                    RoundedCornersTransformation.CornerType.ALL
                )
            )
        }
    }

    return asBitmap()
        .apply(options)
        .load(url)
}

fun provideGenericRequestOptions(url: String, isPreloading: Boolean) =
    RequestOptions
        .diskCacheStrategyOf(DiskCacheStrategy.ALL)
        .signature(ObjectKey(url.plus(if (isPreloading) "_preloading" else "_not_preloading")))