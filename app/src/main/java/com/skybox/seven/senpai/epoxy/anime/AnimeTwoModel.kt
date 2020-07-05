package com.skybox.seven.senpai.epoxy.anime

import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.airbnb.epoxy.preload.Preloadable
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.skybox.seven.senpai.R
import com.skybox.seven.senpai.epoxy.BaseEpoxyHolder
import com.skybox.seven.senpai.ui.main.loadImage

@EpoxyModelClass(layout = R.layout.model_anime_two)
abstract class AnimeTwoModel: EpoxyModelWithHolder<AnimeTwoModel.AnimeTwoHolder>() {
    val holderType = 1
    @EpoxyAttribute
    lateinit var image: String
    @EpoxyAttribute
    lateinit var title: String
    @EpoxyAttribute
    var episodes: Int = 0
    @EpoxyAttribute
    lateinit var type: String
    @EpoxyAttribute
    var preloading: Boolean = false

    override fun getDefaultLayout(): Int {
        return R.layout.model_anime_two
    }

    override fun createNewHolder(): AnimeTwoHolder {
        return AnimeTwoHolder()
    }

    override fun bind(holder: AnimeTwoHolder) {
        super.bind(holder)
        holder.glide.loadImage(image, preloading, holderType).into(holder.image)
        holder.title.text = title
        holder.type.text = type
        holder.episodes.text = "$episodes episodes"
    }

    override fun unbind(holder: AnimeTwoHolder) {
        holder.glide.clear(holder.image)
        holder.image.setImageDrawable(null)
    }

    class AnimeTwoHolder: BaseEpoxyHolder(), Preloadable {
        val title by bind<TextView>(R.id.model_title)
        val type by bind<Chip>(R.id.model_type)
        val episodes by bind<TextView>(R.id.model_episodes)

        val image by bind<ImageView>(R.id.model_image)
        val glide by lazy { Glide.with(image.context) }
        override val viewsToPreload by lazy { listOf(image) }
    }
}