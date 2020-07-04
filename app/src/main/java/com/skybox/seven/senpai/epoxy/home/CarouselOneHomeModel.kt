package com.skybox.seven.senpai.epoxy.home

import android.content.Context
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.ModelView
import com.skybox.seven.senpai.util.CenterZoomLinearLayoutManager


@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class CarouselOneHomeModel(context: Context?) : Carousel(context) {

    override fun createLayoutManager(): LayoutManager {
        return CenterZoomLinearLayoutManager(context)
    }
}