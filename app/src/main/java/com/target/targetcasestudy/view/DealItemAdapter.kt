package com.target.targetcasestudy.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.target.targetcasestudy.R
import com.target.targetcasestudy.model.Product
import com.target.targetcasestudy.model.Products

class DealItemAdapter(private val products: List<Product>) : RecyclerView.Adapter<DealItemViewHolder>() {

  //private lateinit var products: List<Product>

//  fun setProducts(products: Products) {
//    this.products = products.products
//  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealItemViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val view = inflater.inflate(R.layout.deal_list_item, parent, false)
    return DealItemViewHolder(view)
  }

  override fun getItemCount(): Int {
    return products.size
  }

  override fun onBindViewHolder(viewHolder: DealItemViewHolder, position: Int) {
    val item = products[position]
    viewHolder.productTitle = item.title
    viewHolder.productSalePrice = item.salePrice?.displayString
  }
}

class DealItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

  var productTitle: String? = null
  var productSalePrice: String? = null

  init {
    itemView.findViewById<TextView>(R.id.deal_list_item_title)
    itemView.findViewById<TextView>(R.id.deal_list_item_price)
  }
}