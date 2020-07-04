package com.skybox.seven.senpai.epoxy.home

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.ModelView

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class CarouselOneHomeModel(context: Context?) : Carousel(context) {
    override fun getLayoutManager(): LayoutManager? {
        return LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }
}