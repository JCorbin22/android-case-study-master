package com.target.targetcasestudy.services

import com.target.targetcasestudy.model.Product
import com.target.targetcasestudy.model.Products
import com.target.targetcasestudy.model.ProductsResponse
import retrofit2.Call
import retrofit2.http.GET

interface ProductsService {
    @GET("mobile_case_study_deals/v1/deals")
    fun listProducts(): Call<Products>
}