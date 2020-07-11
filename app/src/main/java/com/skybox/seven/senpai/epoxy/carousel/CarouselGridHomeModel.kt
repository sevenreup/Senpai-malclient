package com.skybox.seven.senpai.epoxy.carousel

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.ModelView
import com.skybox.seven.senpai.util.CenterZoomLinearLayoutManager

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class CarouselGridHomeModel(context: Context): Carousel(context) {
    override fun createLayoutManager(): LayoutManager {
        return GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
    }
}