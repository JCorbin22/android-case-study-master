package com.target.targetcasestudy.services

import com.target.targetcasestudy.model.Products
import retrofit2.Call
import retrofit2.http.GET

/**
 * Retrofit service used to retrieve the list of Products from the API.
 */
interface ProductsService {

    /**
     * Gets the general list of Products from the deals endpoint.
     */
    @GET("mobile_case_study_deals/v1/deals")
    fun listProducts(): Call<Products>
}