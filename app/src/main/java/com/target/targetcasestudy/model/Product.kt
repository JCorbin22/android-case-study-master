package com.target.targetcasestudy.model

data class Product(val aisle: String,
                   val description: String,
                   val id: Int,
                   val imageUrl: String?,
                   val regularPrice: Price,
                   val salePrice: Price?,
                   val title: String)
