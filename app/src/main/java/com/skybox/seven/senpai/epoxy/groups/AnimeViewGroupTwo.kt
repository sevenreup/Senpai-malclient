package com.skybox.seven.senpai.epoxy.groups

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.EpoxyModelGroup
import com.skybox.seven.senpai.R
import com.skybox.seven.senpai.api.jikan.model.Anime
import com.skybox.seven.senpai.epoxy.HeaderOneModelModel_
import com.skybox.seven.senpai.epoxy.anime.AnimeTwoModel_
import com.skybox.seven.senpai.epoxy.carousel.CarouselListHomeModelModel_
import com.skybox.seven.senpai.epoxy.carousel.CarouselOneHomeModelModel_

class AnimeViewGroupTwo(
    title: String,
    animeList: List<Anime>
) : EpoxyModelGroup(
    R.layout.model_group_anime_one,
    buildModels(
        title,
        animeList
    )
) {


    companion object {
        private fun buildModels(title: String, animeList: List<Anime>): List<EpoxyModel<*>> {
            val modelList: ArrayList<EpoxyModel<*>> = ArrayList()
            modelList.add(
                HeaderOneModelModel_().id(title).modelTitle(title)
            )
            animeList.forEach {
                modelList.add(
                    AnimeTwoModel_().id(it.malId)
                        .title(it.title)
                        .type(it.type)
                        .episodes(it.episodes)
                        .image(it.imageUrl).preloading(true))
            }
            return modelList
        }
    }

}