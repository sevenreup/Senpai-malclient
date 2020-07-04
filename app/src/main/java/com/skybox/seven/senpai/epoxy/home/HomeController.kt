package com.skybox.seven.senpai.epoxy.home

import android.content.Context
import com.airbnb.epoxy.Typed2EpoxyController
import com.skybox.seven.senpai.api.jikan.model.Anime
import com.skybox.seven.senpai.epoxy.anime.AnimeOneModel_
import com.skybox.seven.senpai.epoxy.anime.AnimeViewGroupOne
import java.util.*

class HomeController(val context: Context?): Typed2EpoxyController<Boolean, List<Anime>>() {


    override fun buildModels(loading: Boolean?, animeList: List<Anime>?) {
        val modelList: ArrayList<AnimeOneModel_> = ArrayList()

        animeList?.forEach {
            modelList.add(AnimeOneModel_().id(it.malId).context(context!!).image(it.imageUrl).preloading(true))
        }

        AnimeViewGroupOne(context!!,"Now Airing", animeList!!).addTo(this)
        AnimeViewGroupOne(context,"Trending", animeList).addTo(this)
        AnimeViewGroupOne(context,"Next Season", animeList).addTo(this)
    }

}