package com.furkansezgin.bringfood.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.furkansezgin.bringfood.model.Bag
import com.furkansezgin.bringfood.model.BagModel
import com.furkansezgin.bringfood.service.Repo
import com.furkansezgin.bringfood.util.Util
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BagViewModel : ViewModel() {
    val bagFoods = MutableLiveData<List<Bag>>()
    var isDelete = MutableLiveData<Boolean>()
    var bagLoader = MutableLiveData<Boolean>()
    private val repo = Repo()
    private lateinit var bagModel: BagModel
    fun getBag() {

        bagLoader.value = true
        repo.getBagFood(Util.userName).enqueue(object : Callback<BagModel>{
            override fun onResponse(call: Call<BagModel>, response: Response<BagModel>) {
                if (response.isSuccessful) {
                    bagModel = response.body()!!
                    bagFoods.value = bagModel.bag
                    bagLoader.value = false
                }else{
                    println("abcdef ${bagFoods.value}")
                }
            }

            override fun onFailure(call: Call<BagModel>, t: Throwable) {
                bagLoader.value = false
                bagFoods.value = emptyList()
            }
        })
    }
    fun deleteFood(sepetYemekId: Int, kullaniciAdi: String) {
        isDelete.value = false
        repo.deleteFood(sepetYemekId,kullaniciAdi).enqueue(object : Callback<BagModel>{
            override fun onResponse(call: Call<BagModel>, response: Response<BagModel>) {
                if (response.isSuccessful) {
                    isDelete.value = true
                    bagLoader.value = false
                }
            }

            override fun onFailure(call: Call<BagModel>, t: Throwable) {
                isDelete.value = false
            }
        })
    }

}