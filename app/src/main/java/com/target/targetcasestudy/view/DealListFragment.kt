package com.target.targetcasestudy.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.target.targetcasestudy.R
import com.target.targetcasestudy.model.Product
import com.target.targetcasestudy.model.Products
import com.target.targetcasestudy.viewmodel.DealListViewModel

class DealListFragment : Fragment() {

  private lateinit var recyclerView: RecyclerView
  private lateinit var progressBar: ProgressBar
  private lateinit var productsAdapter: DealItemAdapter

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val view =  inflater.inflate(R.layout.fragment_deal_list, container, false)
    progressBar = view.findViewById(R.id.progress_bar)
    recyclerView = view.findViewById(R.id.recycler_view)

    return view
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    val viewModel: DealListViewModel by viewModels()
    viewModel.isLoading().observe(this, this::updateProgressBar)
    viewModel.getDeals().observe(this, this::updateDealsList)
    super.onCreate(savedInstanceState)
  }

  private fun updateProgressBar(isLoading: Boolean) {
    if(isLoading) {
      progressBar.visibility = View.VISIBLE
      // recyclerView.visibility = View.GONE
    } else {
      progressBar.visibility = View.GONE
      // recyclerView.visibility = View.VISIBLE
    }
  }

  private fun updateDealsList(products: Products) {
    productsAdapter = DealItemAdapter(products.products)
    recyclerView.layoutManager = LinearLayoutManager(context)
    recyclerView.adapter = productsAdapter
  }
}
