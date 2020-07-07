package com.skybox.seven.senpai.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.skybox.seven.senpai.R
import com.skybox.seven.senpai.util.HideBottomViewOnScrollBehavior
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*

private const val TAG = "HomeActivity"

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    lateinit var viewModel: HomeSharedViewModel
    var scrollBehavior: HideBottomViewOnScrollBehavior<BottomNavigationView> = HideBottomViewOnScrollBehavior<BottomNavigationView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        viewModel = ViewModelProvider(this).get(HomeSharedViewModel::class.java)
        bottom_nav.setupWithNavController(findNavController(R.id.nav_host_fragment))

        val params: CoordinatorLayout.LayoutParams = bottom_nav.layoutParams as CoordinatorLayout.LayoutParams
        params.behavior = scrollBehavior

        viewModel.scrollDirection.observe(this, Observer {
            scrollBehavior.simulateScroll(bottom_nav, it)
        })
    }
}