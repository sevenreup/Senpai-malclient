package com.skybox.seven.senpai.epoxy.groups

import android.view.View
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.EpoxyModelGroup
import com.airbnb.epoxy.OnModelClickListener
import com.skybox.seven.senpai.R
import com.skybox.seven.senpai.api.jikan.model.Anime
import com.skybox.seven.senpai.epoxy.HeaderOneModelModel_
import com.skybox.seven.senpai.epoxy.anime.AnimeOneModel
import com.skybox.seven.senpai.epoxy.anime.AnimeOneModel_
import com.skybox.seven.senpai.epoxy.anime.AnimeTwoModel
import com.skybox.seven.senpai.epoxy.anime.AnimeTwoModel_
import com.skybox.seven.senpai.epoxy.carousel.CarouselListHomeModelModel_
import com.skybox.seven.senpai.epoxy.carousel.CarouselOneHomeModelModel_
import com.skybox.seven.senpai.epoxy.home.HomeController

class AnimeViewGroupTwo(
    title: String,
    animeList: List<Anime>,
    val callbacks: HomeController.BasicControllerCallbacks
) : EpoxyModelGroup(
    R.layout.model_group_anime_one,
    buildModels(
        title,
        animeList,
        callbacks
    )
) {


    companion object {
        private fun buildModels(title: String, animeList: List<Anime>, callbacks: HomeController.BasicControllerCallbacks): List<EpoxyModel<*>> {
            val modelList: ArrayList<EpoxyModel<*>> = ArrayList()
            modelList.add(
                HeaderOneModelModel_().id(title).modelTitle(title)
            )
            animeList.forEach {
                modelList.add(
                    AnimeTwoModel_().id(it.malId)
                        .title(it.title)
                        .type(it.type)
                        .episodes(if (it.episodes == null) 0 else it.episodes)
                        .animeClickListener { animeTwoModel_: AnimeTwoModel_, animeTwoHolder: AnimeTwoModel.AnimeTwoHolder, view: View, i: Int ->
                            callbacks.onAnimeClick(it)
                        }
                        .image(it.imageUrl)
                        .preloading(true))
            }
            return modelList
        }
    }

}