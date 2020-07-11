package com.skybox.seven.senpai.epoxy.anime

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.ColorUtils
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.airbnb.epoxy.preload.Preloadable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.DrawableCrossFadeTransition
import com.bumptech.glide.request.transition.Transition
import com.skybox.seven.senpai.R
import com.skybox.seven.senpai.epoxy.BaseEpoxyHolder
import com.skybox.seven.senpai.ui.main.loadImage
import com.skybox.seven.senpai.util.getDarkSwatch
import com.skybox.seven.senpai.util.getLightSwatch
import com.skybox.seven.senpai.util.getMode
import jp.wasabeef.glide.transformations.CropCircleTransformation

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
        holder.glide.loadImage(image, preloading, holderType)
            .circleCrop()
            .listener(object: RequestListener<Bitmap> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    isFirstResource: Boolean
                ): Boolean {
                    TODO("Not yet implemented")
                }

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    if (resource != null) {
                        Palette.from(resource).generate {
                            val swatch: Palette.Swatch = if (getMode(holder.image.context) == Configuration.UI_MODE_NIGHT_NO) getLightSwatch(it!!) else getDarkSwatch(it!!)
                            holder.image.setBackgroundColor(ColorUtils.setAlphaComponent(swatch.rgb, 255))
                        }
                        target?.onResourceReady(resource, null)
                    }
                    return true
                }

            })
            .into(holder.top)
//        holder.glide.loadImage(image, preloading, holderType).into(holder.image)
        holder.setViewClickListener(animeClickListener)
    }

    override fun unbind(holder: AnimeOneHolder) {
        holder.glide.clear(holder.image)
        holder.top.setImageDrawable(null)
    }
    class AnimeOneHolder: BaseEpoxyHolder(), Preloadable {
        val title by bind<TextView>(R.id.model_title)
        val image by bind<View>(R.id.model_image)
        val top by bind<ImageView>(R.id.model_image_top)
        val glide by lazy { Glide.with(image.context) }
        override val viewsToPreload by lazy { listOf(image) }
    }
}