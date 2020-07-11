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
import com.skybox.seven.senpai.epoxy.carousel.CarouselOneHomeModelModel_
import com.skybox.seven.senpai.epoxy.home.HomeController

class AnimeViewGroupOne(
    title: String,
    animeList: List<Anime>,
    val callbacks: HomeController.BasicControllerCallbacks
) : EpoxyModelGroup(R.layout.model_group_anime_one,
    buildModels(
        title,
        animeList,
        callbacks
    )
) {


    companion object {
        private fun buildModels(title: String, animeList: List<Anime>, callbacks: HomeController.BasicControllerCallbacks): List<EpoxyModel<*>> {
            val modelList: ArrayList<EpoxyModel<*>> = ArrayList()
            val animeModelList: ArrayList<EpoxyModel<*>> = ArrayList()
            animeList.forEach {
                animeModelList.add(
                    AnimeOneModel_().id(it.malId)
                        .image(it.imageUrl)
                        .title(it.title)
                        .animeClickListener { animeOneModel_: AnimeOneModel_, animeOneHolder: AnimeOneModel.AnimeOneHolder, view: View, i: Int ->
                            callbacks.onAnimeClick(it)
                        }
                        .preloading(true))
            }
            modelList.add(
                HeaderOneModelModel_().id(title).modelTitle(title)
            )
            modelList.add(
                CarouselOneHomeModelModel_().id("airing").numViewsToShowOnScreen(2.1f).models(animeModelList)
            )
            return modelList
        }
    }

}