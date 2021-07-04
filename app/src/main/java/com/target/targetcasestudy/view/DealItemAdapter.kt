package com.target.targetcasestudy.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.target.targetcasestudy.R
import com.target.targetcasestudy.interfaces.IDealClickDelegate
import com.target.targetcasestudy.model.Product

class DealItemAdapter(private val products: List<Product>) : RecyclerView.Adapter<DealItemViewHolder>() {

  lateinit var dealClickDelegate: IDealClickDelegate

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
    viewHolder.productTitleTextView.text = item.title
    viewHolder.productSalePriceTextView.text = item.salePrice?.displayString ?: item.regularPrice.displayString
    viewHolder.dealItemLayout.setOnClickListener {
      dealClickDelegate.dealClicked(item.id)
    }
  }
}

class DealItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
  var dealItemLayout: LinearLayout = itemView.findViewById(R.id.deal_list_item)
  var productTitleTextView: TextView = itemView.findViewById(R.id.deal_list_item_title)
  var productSalePriceTextView: TextView = itemView.findViewById(R.id.deal_list_item_price)
}