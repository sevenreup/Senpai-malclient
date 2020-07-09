package com.skybox.seven.senpai.epoxy.anime

import android.content.Context
import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.AfterPropsSet
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.google.android.material.card.MaterialCardView
import com.skybox.seven.senpai.R

@ModelView(autoLayout = ModelView.Size.WRAP_WIDTH_WRAP_HEIGHT)
class PagerTabIndicatorModel @JvmOverloads constructor(context: Context)
    : MaterialCardView(context) {

    private lateinit var titleView: TextView

    init {
        View.inflate(context, R.layout.model_anime_tab_indicator, this)
        titleView = findViewById(R.id.tab_title)
    }

    @TextProp
    lateinit var title: CharSequence

    @AfterPropsSet
    fun useProps() {
        titleView.text = title
    }
}