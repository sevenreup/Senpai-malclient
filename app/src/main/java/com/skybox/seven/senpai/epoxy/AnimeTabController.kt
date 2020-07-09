package com.skybox.seven.senpai.epoxy

import android.util.Log
import com.airbnb.epoxy.TypedEpoxyController
import com.skybox.seven.senpai.epoxy.anime.PagerTabIndicatorModelModel_

class AnimeTabController(private val listener: (Int) -> Unit): TypedEpoxyController<List<String>>() {

    companion object {
        const val defaultScale = 0.9f
        const val maxScale = 1.15f
    }

    override fun buildModels(data: List<String>?) {
        data?.forEach {
            PagerTabIndicatorModelModel_().id(it).title(it)
                .listener { _,_,_,position -> listener(position)}
                .addTo(this)
        }
    }
}