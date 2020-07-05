package com.skybox.seven.senpai.epoxy.home

import android.content.Context
import com.airbnb.epoxy.Typed2EpoxyController
import com.skybox.seven.senpai.api.jikan.model.Anime
import com.skybox.seven.senpai.epoxy.anime.AnimeOneModel_
import com.skybox.seven.senpai.epoxy.groups.AnimeViewGroupOne
import com.skybox.seven.senpai.epoxy.groups.AnimeViewGroupTwo
import java.util.*

class HomeController(val context: Context?): Typed2EpoxyController<Boolean, List<Anime>>() {


    override fun buildModels(loading: Boolean?, animeList: List<Anime>?) {
        val modelList: ArrayList<AnimeOneModel_> = ArrayList()

        animeList?.forEach {
            modelList.add(AnimeOneModel_().id(it.malId).image(it.imageUrl).preloading(true))
        }

        AnimeViewGroupOne(
            "Now Airing",
            animeList!!
        ).addTo(this)
        AnimeViewGroupTwo(
            "Trending",
            animeList
        ).addTo(this)
        AnimeViewGroupOne(
            "Next Season",
            animeList
        ).addTo(this)
    }

}