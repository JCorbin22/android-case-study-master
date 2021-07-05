package com.target.targetcasestudy.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.target.targetcasestudy.R
import com.target.targetcasestudy.interfaces.IDealClickDelegate
import com.target.targetcasestudy.model.Products
import com.target.targetcasestudy.viewmodel.ProductListViewModel

class ProductListFragment : Fragment(), IDealClickDelegate {

  private lateinit var recyclerView: RecyclerView
  private lateinit var progressBar: ProgressBar
  private lateinit var productsAdapter: DealItemAdapter

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val view =  inflater.inflate(R.layout.fragment_product_list, container, false)
    progressBar = view.findViewById(R.id.progress_bar)
    recyclerView = view.findViewById(R.id.recycler_view)
    return view
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    // Get the ViewModel for this view
    val viewModel: ProductListViewModel by viewModels()
    // Observe the LiveData from the ViewModel to update the UI
    viewModel.isLoading().observe(this, this::updateProgressBar)
    viewModel.getDeals().observe(this, this::updateProductsList)
    super.onCreate(savedInstanceState)
  }

  /**
   * Shows or hides the progress bar depending on the LiveData Boolean.
   * @param isLoading: Boolean indicating whether or not data is being loaded.
   */
  private fun updateProgressBar(isLoading: Boolean) {
    if(isLoading) {
      progressBar.visibility = View.VISIBLE
    } else {
      progressBar.visibility = View.GONE
    }
  }

  /**
   * Updates the list of products based on the LiveData in the ViewModel, and sets the
   * RecyclerView adapter.
   * @param products: the Products object containing a list of the Products to be displayed.
   */
  private fun updateProductsList(products: Products?) {
    if(products != null) {
      productsAdapter = DealItemAdapter(products.products)
      productsAdapter.dealClickDelegate = this
      recyclerView.layoutManager = LinearLayoutManager(context)
      recyclerView.adapter = productsAdapter
    } else {
      // If data was null, display a toast indicating so
      Toast.makeText(
        this.context,
        "Unable to retrieve product list, please try again later",
        Toast.LENGTH_SHORT).show()
    }

  }

  /**
   * Handles taps on specific rows of the RecyclerView, and launches the details fragment.
   * @param dealId: The ID of the product that is to be displayed in detail.
   */
  override fun dealClicked(dealId: Int) {
    // launch fragment
    val dealItemFragment = ProductDetailsFragment().also {
      it.arguments = bundleOf(Pair("dealId", dealId))
    }
    val fragmentManager = parentFragmentManager
    fragmentManager.beginTransaction()
    dealItemFragment.show(fragmentManager, "DEAL_ITEM_DETAIL")
  }
}
