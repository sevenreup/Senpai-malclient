package com.skybox.seven.senpai.epoxy

import android.content.Context
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.airbnb.epoxy.AfterPropsSet
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.skybox.seven.senpai.R
import kotlinx.android.synthetic.main.view_header_one.view.*

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class HeaderOneModel(context: Context?) : RelativeLayout(context) {
    private val title: TextView

    init {
        View.inflate(context, R.layout.view_header_one, this)
        title = model_title
    }

    @TextProp
    lateinit var modelTitle: CharSequence

    @AfterPropsSet
    fun useProps() {
        title.text = modelTitle
    }
}