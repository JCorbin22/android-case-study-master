package com.target.targetcasestudy.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.target.targetcasestudy.R
import com.target.targetcasestudy.interfaces.IDealClickDelegate
import com.target.targetcasestudy.model.Product
import java.util.*

class DealItemAdapter(
  private val products: List<Product>
  ) : RecyclerView.Adapter<DealItemViewHolder>() {

  lateinit var dealClickDelegate: IDealClickDelegate

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealItemViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val view = inflater.inflate(R.layout.product_list_item, parent, false)
    return DealItemViewHolder(view)
  }

  override fun getItemCount(): Int {
    return products.size
  }

  override fun onBindViewHolder(viewHolder: DealItemViewHolder, position: Int) {
    val item = products[position]

    // Product details
    viewHolder.productTitleTextView.text = item.title
    viewHolder.productSalePriceTextView.text = item.salePrice?.displayString
      ?: item.regularPrice.displayString
    viewHolder.productAisleTextView.text = item.aisle.toUpperCase(Locale.US)

    // Load image from URL, set content description for accessibility
    viewHolder.productImageView.contentDescription = "Image of product ${item.title}"
    if(item.imageUrl != null) {
      Picasso.get().load(item.imageUrl)
        .placeholder(R.drawable.ic_launcher_foreground)
        .into(viewHolder.productImageView)
    }

    // Handle taps on the entire row object (would change if actions were expected on "ship" text)
    viewHolder.dealItemLayout.setOnClickListener {
      dealClickDelegate.dealClicked(item.id)
    }

    // Do not display the divider line if it is the last position in the list
    if(position == itemCount - 1) {
      viewHolder.productListDivider.visibility = View.GONE
    } else {
      viewHolder.productListDivider.visibility = View.VISIBLE
    }
  }
}

class DealItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
  var dealItemLayout: LinearLayoutCompat = itemView.findViewById(R.id.product_list_item_layout)
  var productTitleTextView: TextView = itemView.findViewById(R.id.product_list_item_title_tv)
  var productSalePriceTextView: TextView = itemView.findViewById(R.id.product_list_item_price_tv)
  var productAisleTextView: TextView = itemView.findViewById(R.id.product_list_item_aisle_tv)
  var productImageView: ImageView = itemView.findViewById(R.id.product_detail_image_view)
  var productListDivider: View = itemView.findViewById(R.id.product_list_item_divider_view)
}