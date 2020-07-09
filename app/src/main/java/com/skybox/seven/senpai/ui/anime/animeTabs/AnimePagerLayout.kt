package com.skybox.seven.senpai.ui.anime.animeTabs

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.skybox.seven.senpai.R

class AnimePagerLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : RelativeLayout(context, attrs, defStyleAttr)
{
    init {
        View.inflate(context, R.layout.layout_anime_pager,this)

    }
}