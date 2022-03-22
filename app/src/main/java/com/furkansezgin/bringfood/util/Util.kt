package com.furkansezgin.bringfood.util

import android.widget.ImageView
import com.squareup.picasso.Picasso

class Util {
    companion object{
        val userName = "furkan_sezgin"
        val BASE_URL = "http://kasimadalan.pe.hu/yemekler/"
        val BASE_IMAGE_URL ="http://kasimadalan.pe.hu/yemekler/resimler/"
        fun putImageWithPicasso(url: String, imageView: ImageView){
            Picasso.get()
                .load(Util.BASE_IMAGE_URL +url)
                .resize(300, 300)
                .centerCrop()
                .into(imageView)
        }
    }
}