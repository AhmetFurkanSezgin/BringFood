package com.furkansezgin.bringfood.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Food(
    @SerializedName("yemek_id")
    @Expose
    val yemekId: Int?,
    @SerializedName("yemek_adi")
    @Expose
    val yemekAdi: String?,
    @SerializedName("yemek_resim_adi")
    @Expose
    val yemekResimAdi: String?,
    @SerializedName("yemek_fiyat")
    @Expose
    val yemekFiyat: Int?
) : Parcelable

