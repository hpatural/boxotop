package com.hugo.boxotop.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

/**
 * Created by hpatural on 26/03/2018.
 */
@GlideModule
class ImageUtils: AppGlideModule() {

    companion object {
        /**
         * Load the local image or download it and save it
         */
        fun loadImage(context: Context, imageView: ImageView, imagePath: String) {
            Glide.with(context)
                    .load(imagePath)
                    .into(imageView)

        }
    }
}