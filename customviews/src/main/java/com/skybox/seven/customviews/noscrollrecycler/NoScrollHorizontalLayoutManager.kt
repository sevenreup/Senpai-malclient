package com.skybox.seven.customviews.noscrollrecycler

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NoScrollHorizontalLayoutManager(context: Context) : LinearLayoutManager(context, RecyclerView.HORIZONTAL, false) {
    var canScrollHorizontally = false

    override fun canScrollHorizontally(): Boolean = canScrollHorizontally
}