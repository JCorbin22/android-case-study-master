package com.target.targetcasestudy.view

import android.app.Dialog
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import com.target.targetcasestudy.R
import com.target.targetcasestudy.model.Product
import com.target.targetcasestudy.viewmodel.ProductDetailsViewModel
import com.target.targetcasestudy.viewmodel.ProductViewModelFactory

/**
 * Product details for a specific item represented by a full screen DialogFragment.
 * Details will be loaded from a Retrofit service by using a product ID, provided by the
 * previous product list fragment when an item is selected.
 */
class ProductDetailsFragment : DialogFragment() {

  private lateinit var progressBar: ProgressBar
  private lateinit var titleTextView: TextView
  private lateinit var salePriceTextView: TextView
  private lateinit var regPriceTextView: TextView
  private lateinit var regPriceLabel: TextView
  private lateinit var descriptionTextView: TextView
  private lateinit var productImageView: ImageView
  private lateinit var addToListButton: Button
  private lateinit var addToCartButton: Button

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
    // Retrieve the ViewModel for this view
    val viewModel = ViewModelProvider(this,
      ProductViewModelFactory(arguments?.getInt("dealId")!!)) // enforcing non-null in list view
      .get(ProductDetailsViewModel::class.java)
    // Observe LiveData in ViewModel
    viewModel.isLoading().observe(this, this::updateProgressBar)
    viewModel.getDeals().observe(this, this::updateProductDetails)
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val view = inflater.inflate(R.layout.fragment_product_details, container, false)
    progressBar = view.findViewById(R.id.details_progress_bar)
    titleTextView = view.findViewById(R.id.product_detail_title_tv)
    salePriceTextView = view.findViewById(R.id.product_detail_sale_price_tv)
    regPriceTextView = view.findViewById(R.id.product_detail_reg_price_tv)
    regPriceLabel = view.findViewById(R.id.reg_price_label)
    descriptionTextView = view.findViewById(R.id.product_detail_desc_tv)
    productImageView = view.findViewById(R.id.product_detail_image_view)
    addToListButton = view.findViewById(R.id.add_to_list_button)
    addToCartButton = view.findViewById(R.id.add_to_cart_button)
    return view
  }

  /**
   * Shows or hides the progress bar depending on the LiveData object value.
   * @param isLoading: The Boolean value indicating whether or not the data is still loading.
   */
  private fun updateProgressBar(isLoading: Boolean) {
    if(isLoading) {
      progressBar.visibility = View.VISIBLE
    } else {
      progressBar.visibility = View.GONE
    }
  }

  /**
   * Updates the UI with the relevant product details when the LiveData object is updated.
   * @param product: The Product object retrieved from the LiveData object.
   */
  private fun updateProductDetails(product: Product?) {
    if(product != null) {
      // Set product details
      titleTextView.text = product.title
      descriptionTextView.text = product.description
      if(product.salePrice != null) {
        salePriceTextView.text = product.salePrice.displayString
        regPriceTextView.text = product.regularPrice.displayString
        regPriceTextView.paintFlags = regPriceTextView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        regPriceTextView.visibility = View.VISIBLE
        regPriceLabel.visibility = View.VISIBLE
      } else {
        salePriceTextView.text = product.regularPrice.displayString
        regPriceLabel.visibility = View.GONE
        regPriceTextView.visibility = View.GONE
      }

      // Set image details
      productImageView.contentDescription = "Image of product ${product.title}"
      if(product.imageUrl != null) {
        Picasso.get().load(product.imageUrl)
          .placeholder(R.drawable.ic_launcher_foreground)
          .into(productImageView)
      }

      addToListButton.setOnClickListener {
        // Future: add to a caching repository
        dialog?.dismiss()
      }
      addToCartButton.setOnClickListener {
        // Future: add to a caching repository
        dialog?.dismiss()
      }
    } else {
      // If the data came back null, display a toast message
      Toast.makeText(
        this.context,
        "Unable to retrieve product details, please try again later",
        Toast.LENGTH_SHORT).show()
    }
  }
}
