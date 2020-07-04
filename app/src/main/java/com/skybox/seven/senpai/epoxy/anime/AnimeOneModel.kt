package com.skybox.seven.senpai.epoxy.anime

import android.content.Context
import android.widget.ImageView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.skybox.seven.senpai.R
import com.skybox.seven.senpai.epoxy.BaseEpoxyHolder

@EpoxyModelClass(layout = R.layout.model_anime_one)
abstract class AnimeOneModel: EpoxyModelWithHolder<AnimeOneModel.AnimeOneHolder>() {
    @EpoxyAttribute lateinit var image: String
    @EpoxyAttribute lateinit var context: Context

    override fun getDefaultLayout(): Int {
        return R.layout.model_anime_one
    }

    override fun createNewHolder(): AnimeOneHolder {
        return AnimeOneHolder()
    }

    override fun bind(holder: AnimeOneHolder) {
        super.bind(holder)
        Glide.with(context).load(image).into(holder.image)
    }

    class AnimeOneHolder: BaseEpoxyHolder() {
        val image by bind<ImageView>(R.id.model_image)
    }
}