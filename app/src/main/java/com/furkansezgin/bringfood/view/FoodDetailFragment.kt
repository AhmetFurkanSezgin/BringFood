package com.furkansezgin.bringfood.view

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.furkansezgin.bringfood.R
import com.furkansezgin.bringfood.model.Food
import com.furkansezgin.bringfood.util.Util
import com.furkansezgin.bringfood.viewmodel.FoodDetailViewModel
import kotlinx.android.synthetic.main.fragment_food_detail.*

class FoodDetailFragment : Fragment() {

    private lateinit var foodDetailViewModel: FoodDetailViewModel
    private lateinit var food: Food

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_food_detail, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            food = FoodDetailFragmentArgs.fromBundle(it).currentFood
        }
        foodDetailViewModel = ViewModelProvider(this).get(FoodDetailViewModel::class.java)
        foodDetailViewModel.foodLiveData.observe(viewLifecycleOwner, Observer {
            foodNameTextView.text = it.yemekFiyat.toString() + " ₺"
            priceValueTextView.text = it.yemekAdi
            it.yemekResimAdi?.let { it1 -> Util.putImageWithPicasso(it1, detailImageView) }
        })
        foodDetailViewModel.detailLoader.observe(viewLifecycleOwner, Observer {
            if (it) {
                detailLoading.visibility = View.VISIBLE
            } else {
                detailLoading.visibility = View.INVISIBLE
            }
        })
        foodDetailViewModel.settingFood(food)
        goToChart.setOnClickListener {
            val action = FoodDetailFragmentDirections.actionFoodDetailFragmentToBagFragment()
            Navigation.findNavController(requireView()).navigate(action)
        }
        addButton.setOnClickListener {
            numberEditText.hideKeyboard()
            detailLoading.visibility = View.VISIBLE
            if (numberEditText.text.toString() == "" || numberEditText.text.toString()
                    .toInt() < 1
            ) {
                numberEditText.text = null
                detailLoading.visibility = View.INVISIBLE
                Toast.makeText(context, "Lütfen Geçerli Bir Sayı Giriniz", Toast.LENGTH_LONG).show()
            } else {
                food.yemekAdi?.let { it1 ->
                    food.yemekResimAdi?.let { it2 ->
                        food.yemekFiyat?.let { it3 ->
                            foodDetailViewModel.addFood(
                                it1,
                                it2, it3, numberEditText.text.toString().toInt(), Util.userName
                            )
                        }
                    }
                    numberEditText.text = null
                }
            }

        }
        foodDetailViewModel.isAdd.observe(viewLifecycleOwner, Observer {
            if(it){
                Toast.makeText(context, "Yemek Sepete Eklendi!", Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    override fun onStop() {
        super.onStop()
        foodDetailViewModel.isAdd.value = false
    }
}