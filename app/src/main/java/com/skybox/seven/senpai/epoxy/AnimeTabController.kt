package com.skybox.seven.senpai.epoxy

import com.airbnb.epoxy.TypedEpoxyController
import com.skybox.seven.senpai.epoxy.anime.PagerTabIndicatorModelModel_

class AnimeTabController(): TypedEpoxyController<List<String>>() {

    companion object {
        const val defaultScale = 0.9f
        const val maxScale = 1.15f
    }

    override fun buildModels(data: List<String>?) {
        data?.forEach {
            PagerTabIndicatorModelModel_().id(it).title(it).addTo(this)
        }
    }
}