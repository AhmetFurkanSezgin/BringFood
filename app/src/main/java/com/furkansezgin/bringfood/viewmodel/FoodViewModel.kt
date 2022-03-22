package com.furkansezgin.bringfood.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.furkansezgin.bringfood.model.Food
import com.furkansezgin.bringfood.model.FoodModel
import com.furkansezgin.bringfood.service.Repo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodViewModel : ViewModel() {
    val foods = MutableLiveData<List<Food>>()
    var foodloader = MutableLiveData<Boolean>()
    private lateinit var foodModel: FoodModel
    private val repo = Repo()

    fun getData() {
        foodloader.value = true
        repo.getFood().enqueue(object : Callback<FoodModel>{
            override fun onResponse(call: Call<FoodModel>, response: Response<FoodModel>) {
                if (response.isSuccessful) {
                    foodloader.value = false
                    foodModel = response.body()!!
                    foods.value = foodModel.food
                }
            }
            override fun onFailure(call: Call<FoodModel>, t: Throwable) {}
        })
    }
}