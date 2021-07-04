package com.target.targetcasestudy.view

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import com.target.targetcasestudy.R
import com.target.targetcasestudy.model.Product
import com.target.targetcasestudy.viewmodel.DealItemViewModel
import com.target.targetcasestudy.viewmodel.ProductViewModelFactory

class DealItemFragment : DialogFragment() {

  lateinit var progressBar: ProgressBar
  lateinit var titleTextView: TextView
  lateinit var priceTextView: TextView
  lateinit var productImageView: ImageView

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    val dialog: Dialog = super.onCreateDialog(savedInstanceState)
    dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
    return dialog
  }

  override fun onStart() {
    super.onStart()
    if(dialog != null) {
      dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
      dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    val viewModel = ViewModelProvider(this, ProductViewModelFactory(arguments?.getInt("dealId")!!)).get(DealItemViewModel::class.java) // do something nicer
    viewModel.isLoading().observe(this, this::updateProgressBar)
    viewModel.getDeals().observe(this, this::updateProductDetails)
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    val view = inflater.inflate(R.layout.fragment_deal_item, container, false)
    progressBar = view.findViewById(R.id.details_progress_bar)
    titleTextView = view.findViewById(R.id.deal_list_item_title)
    priceTextView = view.findViewById(R.id.deal_list_item_price)
    productImageView = view.findViewById(R.id.deal_list_item_image_view)
    return view
  }

  private fun updateProgressBar(isLoading: Boolean) {
    if(isLoading) {
      progressBar.visibility = View.VISIBLE
    } else {
      progressBar.visibility = View.GONE
    }
  }

  private fun updateProductDetails(product: Product?) {
    if(product != null) {
      titleTextView.text = product.title
      priceTextView.text = product.salePrice?.displayString ?: product.regularPrice.displayString
      if(product.imageUrl != null) {
        Picasso.get().load(product.imageUrl)
          .placeholder(R.drawable.ic_launcher_foreground)
          .into(productImageView)
      }
    } else {
      Toast.makeText(
        this.context,
        "Unable to retrieve product details, please try again later",
        Toast.LENGTH_SHORT).show()
    }
  }
}
