package com.furkansezgin.bringfood.service

import com.furkansezgin.bringfood.model.BagModel
import com.furkansezgin.bringfood.model.FoodModel
import retrofit2.Call
import retrofit2.http.*

interface FoodAPI {

    @GET("tumYemekleriGetir.php")
    fun getFood(): Call<FoodModel>

    @POST("sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    fun getBagFood(@Field("kullanici_adi") kullaniciAdi: String?): Call<BagModel>

    @POST("sepeteYemekEkle.php")
    @FormUrlEncoded
    fun setBagFood(
        @Field("yemek_adi") yemekAdi: String?,
        @Field("yemek_resim_adi") yemekResimAdi: String?,
        @Field("yemek_fiyat") yemekFiyat: Int?,
        @Field("yemek_siparis_adet") yemekSiparisAdet: Int?,
        @Field("kullanici_adi") kullaniciAdi: String?
    ): Call<BagModel>

    @POST("sepettenYemekSil.php")
    @FormUrlEncoded
    fun deleteFood(
        @Field("sepet_yemek_id") sepetYemekId: Int?,
        @Field("kullanici_adi") kullaniciAdi: String?
    ): Call<BagModel>
}