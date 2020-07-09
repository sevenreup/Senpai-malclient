package com.skybox.seven.senpai.views

import android.content.Context
import android.util.AttributeSet
import com.airbnb.epoxy.EpoxyRecyclerView
import com.skybox.seven.customviews.noscrollrecycler.NoScrollHorizontalLayoutManager

class NoScrollRecyclerView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    :EpoxyRecyclerView(context, attrs, defStyleAttr) {
    override fun scrollBy(x: Int, y: Int) {
        val lm = layoutManager as? NoScrollHorizontalLayoutManager
        lm?.canScrollHorizontally = true
        super.scrollBy(x, y)
        lm?.canScrollHorizontally = false
    }
}