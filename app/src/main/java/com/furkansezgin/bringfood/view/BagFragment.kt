package com.furkansezgin.bringfood.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.furkansezgin.bringfood.R
import com.furkansezgin.bringfood.adapter.BagAdapter
import com.furkansezgin.bringfood.viewmodel.BagViewModel
import kotlinx.android.synthetic.main.fragment_bag.*


class BagFragment : Fragment() {
    private lateinit var bagViewModel: BagViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bag, container, false)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bagViewModel = ViewModelProvider(this).get(BagViewModel::class.java)
        val bagAdapter = BagAdapter(arrayListOf(), bagViewModel)
        bagViewModel.getBag()
        recyclerBag.layoutManager = LinearLayoutManager(context)
        recyclerBag.adapter = bagAdapter
        bagViewModel.bagFoods.observe(viewLifecycleOwner, Observer { bag ->
            bagAdapter.updateFoodList(bag)
        })
        bagViewModel.isDelete.observe(viewLifecycleOwner, Observer {
            if (it) {
                bagViewModel.getBag()
            }
        })
        bagViewModel.bagLoader.observe(viewLifecycleOwner, Observer {
            if (it) {
                bagLoading.visibility = View.VISIBLE
            } else {
                bagLoading.visibility = View.INVISIBLE

            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear();
    }

    override fun onStop() {
        super.onStop()
    }
}