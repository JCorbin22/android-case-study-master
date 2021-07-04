package com.target.targetcasestudy.model

import com.google.gson.annotations.SerializedName

data class Product(val aisle: String,
                   val description: String,
                   val id: Int,
                   @SerializedName("image_url") val imageUrl: String?,
                   @SerializedName("regular_price") val regularPrice: Price,
                   @SerializedName("sale_price") val salePrice: Price?,
                   val title: String)
