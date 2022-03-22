package com.furkansezgin.bringfood.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Bag(
    @SerializedName("sepet_yemek_id")
    @Expose
    val sepetYemekId: Int?,
    @SerializedName("yemek_adi")
    @Expose
    val yemekAdi: String?,
    @SerializedName("yemek_resim_adi")
    @Expose
    val yemekResimAdi: String?,
    @SerializedName("yemek_fiyat")
    @Expose
    val yemekFiyat: Int?,
    @SerializedName("yemek_siparis_adet")
    @Expose
    val yemekSiparisAdet: Int?,
    @SerializedName("kullanici_adi")
    @Expose
    val kullaniciAdi: String?
) {
}