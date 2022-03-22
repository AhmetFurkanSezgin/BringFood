package com.furkansezgin.bringfood.service

import com.furkansezgin.bringfood.model.BagModel
import com.furkansezgin.bringfood.model.FoodModel
import com.furkansezgin.bringfood.util.Util
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Repo {
    private val BASE_URL = Util.BASE_URL
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(FoodAPI::class.java)

    fun getFood(): Call<FoodModel>{
        return api.getFood()
    }

    fun getBagFood(kullaniciAdi: String): Call<BagModel>{
        return api.getBagFood(kullaniciAdi)
    }
    fun setBagFood(yemekAdi: String, yemekResimAdi: String, yemekFiyat: Int, yeneksiparisAdet: Int,kullaniciAdi: String): Call<BagModel>{
        return api.setBagFood(yemekAdi,yemekResimAdi,yemekFiyat, yeneksiparisAdet,kullaniciAdi)
    }
    fun deleteFood(sepetYemekId: Int, kullaniciAdi: String): Call<BagModel>{
        return api.deleteFood(sepetYemekId,kullaniciAdi)
    }
}