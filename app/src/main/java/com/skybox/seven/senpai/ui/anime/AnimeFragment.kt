package com.skybox.seven.senpai.ui.anime

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.DrawableCrossFadeTransition
import com.skybox.seven.senpai.adapter.AnimeViewPagerAdapter
import com.skybox.seven.senpai.databinding.FragmentAnimeBinding
import com.skybox.seven.senpai.epoxy.AnimeTabController
import com.skybox.seven.senpai.util.DefaultItemDecorator
import com.skybox.seven.senpai.util.ViewPagerTabsHandler
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.glide.transformations.BlurTransformation

@AndroidEntryPoint
class AnimeFragment : Fragment() {
    private var _binding: FragmentAnimeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AnimeViewModel by activityViewModels()
    private val args: AnimeFragmentArgs by navArgs()

    private lateinit var tabsHandler: ViewPagerTabsHandler;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAnimeBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel.activeAnimeData.value = args.anime

        val adapter = AnimeViewPagerAdapter(childFragmentManager, lifecycle)
        val controller = AnimeTabController { binding.animeViewpager.setCurrentItem(it, true)}
        binding.animeViewpager.adapter = adapter
        binding.tabsRecycler.setController(controller)
        binding.tabsRecycler.setItemSpacingPx(100)
        binding.tabsRecycler.addItemDecoration(DefaultItemDecorator(20, 20))
        controller.setData(AnimeViewPagerAdapter.animeTabTitles)

        tabsHandler = ViewPagerTabsHandler(binding.animeViewpager, binding.tabsRecycler)
        tabsHandler.init()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val anime = args.anime
        binding.spotAnimeTitle.text = anime.title
        Glide.with(requireContext()).load(anime.imageUrl)
            .apply(RequestOptions.bitmapTransform(BlurTransformation(18, 3)))
            .into(binding.animeCover)
        Glide.with(requireContext())
            .load(anime.imageUrl)
            .listener(object: RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    TODO("Not yet implemented")
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    if (resource != null) {
                        Palette.from(resource.toBitmap()).generate {
                            viewModel.palleteData.value = it
                        }
                        target?.onResourceReady(resource, DrawableCrossFadeTransition(1000, isFirstResource))
                    }
                    return true
                }

            })
            .into(binding.animeCoverTop)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val numOfTabs = 2
    }
}