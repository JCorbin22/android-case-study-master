package com.target.targetcasestudy.services

import com.target.targetcasestudy.model.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Retrofit service used to retrieve specific product details from the API using a product ID.
 */
interface ProductDetailsService {

    /**
     * Fetches the specific details of the provided Product ID
     * @param productId: the ID of the product to be retrieved.
     */
    @GET("mobile_case_study_deals/v1/deals/{dealId}")
    fun retrieveProductDetails(@Path("dealId") productId: Int): Call<Product>
}