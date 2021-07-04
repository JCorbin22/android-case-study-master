package com.target.targetcasestudy.services

import com.target.targetcasestudy.model.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductDetailsService {

    @GET("mobile_case_study_deals/v1/deals/{dealId}")
    fun retrieveProductDetails(@Path("dealId") dealId: Int): Call<Product>
}