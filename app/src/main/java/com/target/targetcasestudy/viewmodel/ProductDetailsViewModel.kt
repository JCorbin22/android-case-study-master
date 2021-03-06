package com.target.targetcasestudy.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.target.targetcasestudy.model.Product
import com.target.targetcasestudy.services.ProductDetailsService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductDetailsViewModel(private val dealId: Int): ViewModel() {

    private var _isLoading = MutableLiveData<Boolean>().apply { value = false }
    private val _dealDetails: MutableLiveData<Product> by lazy {
        MutableLiveData<Product>().also {
            retrieveProductDetails(dealId)
        }
    }

    // LiveData representing whether data is being loaded or not
    fun isLoading(): LiveData<Boolean> {
        return _isLoading
    }

    // LiveData representing Product data
    fun getDeals(): MutableLiveData<Product> {
        return _dealDetails
    }

    /**
     * Fetches product details from a Retrofit service using the product ID.
     * @param productId: the ID of the product to be retrieved.
     */
    private fun retrieveProductDetails(productId: Int) {
        _isLoading.value = true
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.target.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ProductDetailsService::class.java)
        val products = service.retrieveProductDetails(productId)
        products.enqueue(object : Callback<Product> {
            override fun onResponse(
                call: Call<Product>,
                response: Response<Product>
            ) {
                if(response.isSuccessful) {
                    _dealDetails.value = response.body()
                    _isLoading.value = false
                }
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                _isLoading.value = false
                _dealDetails.value = null
                Log.e(this.javaClass.name.toString(),
                    "Failed to retrieve product list: ${t.message.toString()}")
            }
        })
    }
}