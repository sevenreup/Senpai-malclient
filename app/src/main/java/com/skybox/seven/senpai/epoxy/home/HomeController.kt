package com.skybox.seven.senpai.epoxy.home

import android.content.Context
import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.Typed2EpoxyController
import com.skybox.seven.senpai.api.jikan.model.Anime
import com.skybox.seven.senpai.epoxy.anime.AnimeOneModel_
import java.util.ArrayList

class HomeController(val context: Context?): Typed2EpoxyController<Boolean, List<Anime>>() {


    override fun buildModels(loading: Boolean?, animeList: List<Anime>?) {
        val modelList: ArrayList<AnimeOneModel_> = ArrayList()

        animeList?.forEach {
            context?.let { it1 -> AnimeOneModel_().id(it.malId).context(it1).image(it.imageUrl) }?.let { it2 ->
                modelList.add(
                    it2
                )
            }
        }
        CarouselOneHomeModelModel_().id("home").numViewsToShowOnScreen(2.5f).models(modelList).addTo(this)
    }

}