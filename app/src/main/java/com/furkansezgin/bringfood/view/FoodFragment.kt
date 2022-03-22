package com.furkansezgin.bringfood.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.furkansezgin.bringfood.R
import com.furkansezgin.bringfood.adapter.FoodAdapter
import com.furkansezgin.bringfood.viewmodel.FoodViewModel
import kotlinx.android.synthetic.main.fragment_food.*


class FoodFragment : Fragment() {
    private lateinit var foodViewModel: FoodViewModel
    private val foodAdapter = FoodAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_food, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        foodViewModel = ViewModelProvider(this).get(FoodViewModel::class.java)
        foodViewModel.getData()
        recyclerFood.layoutManager = LinearLayoutManager(context)
        recyclerFood.adapter = foodAdapter
        foodViewModel.foods.observe(viewLifecycleOwner, Observer {
            it?.let {
                foodAdapter.updateFoodList(it)
            }
        })
        foodViewModel.foodloader.observe(viewLifecycleOwner, Observer {
            if (it == false) {
                foodLoading.visibility = View.GONE
            }
        })
        goToChart.setOnClickListener {
            val action = FoodFragmentDirections.actionFoodFragmentToBagFragment()
            Navigation.findNavController(this.requireActivity(),R.id.fragmentContainerView).navigate(action)
        }
    }
}