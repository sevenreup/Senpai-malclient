package com.skybox.seven.senpai.util

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.skybox.seven.customviews.noscrollrecycler.NoScrollHorizontalLayoutManager
import com.skybox.seven.senpai.R
import com.skybox.seven.senpai.epoxy.AnimeTabController
import com.skybox.seven.senpai.ui.anime.AnimeFragment
import com.skybox.seven.senpai.views.NoScrollRecyclerView

@SuppressLint("WrongConstant")
class ViewPagerTabsHandler(private val viewPager2: ViewPager2,
                           private val tabsRecyclerView: NoScrollRecyclerView, col: Int) {
    private val context = viewPager2.context

    private val tabColor: Int by bindColor(context, R.color.tab_unselected_color)
    private var tabSelectedColor: Int = col

    private var totalTabsScroll = 0
    private var currentTab = 0

    fun init() {
        viewPager2.offscreenPageLimit = AnimeFragment.numOfTabs
        tabsRecyclerView.layoutManager = NoScrollHorizontalLayoutManager(context)

        tabsRecyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                totalTabsScroll += dx
            }
        })

        viewPager2.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                val currentTabView = tabsRecyclerView.layoutManager?.findViewByPosition(position) ?: return
                currentTab = position
                val dx = (position + positionOffset) * 64 - totalTabsScroll
                tabsRecyclerView.scrollBy(dx.toInt(), 0)

                val nextTabView = tabsRecyclerView.layoutManager?.findViewByPosition(position + 1)

                val defaultScale: Float = AnimeTabController.defaultScale
                val maxScale: Float = AnimeTabController.maxScale

                currentTabView.setScale(defaultScale + (1 - positionOffset) * (maxScale - defaultScale))
                nextTabView?.setScale(defaultScale + positionOffset * (maxScale - defaultScale))

                currentTabView.findViewById<View>(R.id.background).backgroundTintList =
                    ColorStateList.valueOf(blendColors(tabColor, tabSelectedColor, 1 - positionOffset))
                nextTabView?.findViewById<View>(R.id.background)?.backgroundTintList =
                    ColorStateList.valueOf(blendColors(tabColor, tabSelectedColor, positionOffset))
            }
        })
    }

    fun loadColor(color: Int) {
        tabSelectedColor = color
        val currentTabView = tabsRecyclerView.layoutManager?.findViewByPosition(currentTab) ?: return
        currentTabView.findViewById<View>(R.id.background).backgroundTintList =
            ColorStateList.valueOf(blendColors(tabColor, color, 1 - .8f))
    }

}