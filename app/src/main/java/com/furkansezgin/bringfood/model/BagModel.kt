package com.furkansezgin.bringfood.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BagModel(
    @SerializedName("sepet_yemekler")
    @Expose
    val bag: List<Bag>,
    @SerializedName("success")
    @Expose
    val message: String
) {
}