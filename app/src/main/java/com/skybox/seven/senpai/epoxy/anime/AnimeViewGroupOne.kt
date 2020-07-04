package com.skybox.seven.senpai.epoxy.anime

import android.content.Context
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.EpoxyModelGroup
import com.skybox.seven.senpai.R
import com.skybox.seven.senpai.api.jikan.model.Anime
import com.skybox.seven.senpai.epoxy.HeaderOneModelModel_
import com.skybox.seven.senpai.epoxy.home.CarouselOneHomeModelModel_
import kotlin.collections.ArrayList

class AnimeViewGroupOne(
    private val context: Context,
    private val title: String,
    private var animeList: List<Anime>
) : EpoxyModelGroup(R.layout.model_group_anime_one, buildModels(title, animeList, context)) {


    companion object {
        private fun buildModels(title: String, animeList: List<Anime>, context: Context): List<EpoxyModel<*>> {
            val modelList: ArrayList<EpoxyModel<*>> = ArrayList()
            val animeModelList: ArrayList<EpoxyModel<*>> = ArrayList()
            animeList.forEach {
                animeModelList.add(AnimeOneModel_().id(it.malId).context(context!!).image(it.imageUrl).preloading(true))
            }
            modelList.add(
                HeaderOneModelModel_().id(title).modelTitle(title)
            )
            modelList.add(
                CarouselOneHomeModelModel_().id("airing").numViewsToShowOnScreen(2.5f).models(animeModelList)
            )
            return modelList
        }
    }

}