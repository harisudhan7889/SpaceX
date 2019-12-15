package com.spacex.launch.common

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.annotation.ColorInt
import android.widget.ImageView
import com.spacex.launch.R
import com.spacex.launch.common.widget.CircularImageView
import com.squareup.picasso.Picasso

/**
 * It contains a bunch of related methods,
 * so they can be reused across the application.
 *
 * @author Hari Hara Sudhan.N
 */
object AppUtil {

    /**
     * Loads the image inside the Circular Image View with the provided border color.
     * Image resize is done to increase the image loading performance.
     *
     * @see CircularImageView
     * @param context            Activity context to load the image using picasso.
     * @param imageView          Image Container where the image will be loaded.
     * @param borderColor        Color for the rounded border.
     * @param borderShadowColor  Shadow color for the rounded border.
     */
    fun loadImageIntoCircularView(context: Context, imageView: CircularImageView?,
                                  src: String?, @ColorInt borderColor: Int,
                                  @ColorInt borderShadowColor: Int) {
        imageView?.apply {
            val imageSize = context.resources.getInteger(R.integer.spacex_thumbnail_image_size)
            src?.let {
                Picasso.with(context).load(it).resize(imageSize, imageSize).centerCrop().into(this)
            }
            this.borderColor = borderColor
            this.shadowColor = borderShadowColor
        }
    }

    /**
     * Loads the image in a ImageView.
     * Image resize is done to increase the image loading performance.
     *
     * @param context    Activity context to load the image using picasso.
     * @param imageView  Image Container where the image will be loaded.
     */
    fun loadImageToView(context: Context, imageView: ImageView?,
                        src: String?) {
        imageView?.run {
            val imageSize = context.resources.getInteger(R.integer.spacex_thumbnail_image_size)
            src?.let {
                Picasso.with(context).load(it).resize(imageSize, imageSize).centerCrop().into(this)
            }
        }
    }

    /**
     * Launch the external application.
     *
     * @param context  Activity context to load the image using picasso.
     * @param url      Url to be launched.
     */
    fun launchExternalApp(context: Context, url: String?) {
        url?.let {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        }
    }
}