package com.target.targetcasestudy.model

import com.google.gson.annotations.SerializedName

class ProductsResponse {
    @SerializedName("products")
    var products: Products? = null
}