package com.skybox.seven.senpai.epoxy.anime

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.airbnb.epoxy.preload.Preloadable
import com.bumptech.glide.Glide
import com.skybox.seven.senpai.R
import com.skybox.seven.senpai.epoxy.BaseEpoxyHolder
import com.skybox.seven.senpai.ui.main.loadImage

@EpoxyModelClass(layout = R.layout.model_anime_one)
abstract class AnimeOneModel: EpoxyModelWithHolder<AnimeOneModel.AnimeOneHolder>(){
    val holderType = 0
    @EpoxyAttribute lateinit var image: String
    @EpoxyAttribute lateinit var title: String
    @EpoxyAttribute var preloading: Boolean = false
    @EpoxyAttribute lateinit var animeClickListener: View.OnClickListener

    override fun getDefaultLayout(): Int {
        return R.layout.model_anime_one
    }

    override fun createNewHolder(): AnimeOneHolder {
        return AnimeOneHolder()
    }

    override fun bind(holder: AnimeOneHolder) {
        super.bind(holder)
        holder.title.text = title
        holder.glide.loadImage(image, preloading, holderType).into(holder.image)
        holder.setViewClickListener(animeClickListener)
    }

    override fun unbind(holder: AnimeOneHolder) {
        holder.glide.clear(holder.image)
        holder.image.setImageDrawable(null)
    }
    class AnimeOneHolder: BaseEpoxyHolder(), Preloadable {
        val title by bind<TextView>(R.id.model_title)
        val image by bind<ImageView>(R.id.model_image)
        val glide by lazy { Glide.with(image.context) }
        override val viewsToPreload by lazy { listOf(image) }
    }
}