package com.furkansezgin.bringfood.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FoodModel(
    @SerializedName("yemekler")
    @Expose
    val food: List<Food>,
    @SerializedName("success")
    @Expose
    val message: String
)