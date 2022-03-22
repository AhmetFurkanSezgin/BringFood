package com.furkansezgin.bringfood.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.furkansezgin.bringfood.R
import com.furkansezgin.bringfood.model.Bag
import com.furkansezgin.bringfood.util.Util
import com.furkansezgin.bringfood.viewmodel.BagViewModel
import kotlinx.android.synthetic.main.bag_item_row.view.*
import kotlinx.android.synthetic.main.fragment_bag.view.*
import java.util.ArrayList

class BagAdapter(val bagList: ArrayList<Bag>, val viewModel: BagViewModel) :
    RecyclerView.Adapter<BagAdapter.BagViewHolder>() {
    private var total = 0
    class BagViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BagViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.bag_item_row, parent, false)
        return BagViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: BagViewHolder, position: Int) {
        var totalPrice = 0
        val price = bagList[position].yemekFiyat
        val adet = bagList[position].yemekSiparisAdet!!
        totalPrice = adet * price!!
        holder.view.bagPriceTextView.text = totalPrice.toString() + "₺"
        holder.view.bagNameTextView.text = bagList[position].yemekAdi
        holder.view.adetTextView.text =  "Adet: " + bagList[position].yemekSiparisAdet.toString()
        Util.putImageWithPicasso(
            bagList[position].yemekResimAdi.toString(),
            holder.view.bagImageView
        )
        holder.view.deleteButton.setOnClickListener {
            viewModel.bagLoader.value = true
            bagList[position].sepetYemekId?.let { it1 ->
                viewModel.deleteFood(
                    it1,
                    Util.userName
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return bagList.size
    }

    fun updateFoodList(newBagList: List<Bag>) {
        bagList.clear()
        bagList.addAll(newBagList)
        notifyDataSetChanged()
    }
}