package com.furkansezgin.bringfood.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.furkansezgin.bringfood.R
import com.furkansezgin.bringfood.model.Food
import com.furkansezgin.bringfood.util.Util
import com.furkansezgin.bringfood.view.FoodFragmentDirections
import kotlinx.android.synthetic.main.food_item_row.view.*
import java.util.ArrayList

class FoodAdapter(val foodList: ArrayList<Food>) :
    RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    class FoodViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.food_item_row, parent, false)
        return FoodViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.view.fiyatTextView.text = foodList[position].yemekFiyat.toString() + " ₺"
        holder.view.adTextView.text = foodList[position].yemekAdi
        Util.putImageWithPicasso(
            foodList[position].yemekResimAdi.toString(),
            holder.view.foodImageView
        )
        holder.view.setOnClickListener {
            val action =
                FoodFragmentDirections.actionFoodFragmentToFoodDetailFragment(foodList[position])
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    fun updateFoodList(newFoodList: List<Food>) {
        foodList.clear()
        foodList.addAll(newFoodList)
        notifyDataSetChanged()
    }
}