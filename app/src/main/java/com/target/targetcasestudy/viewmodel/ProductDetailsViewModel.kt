package com.target.targetcasestudy.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.target.targetcasestudy.model.Product
import com.target.targetcasestudy.model.Products
import com.target.targetcasestudy.services.ProductDetailsService
import com.target.targetcasestudy.services.ProductsService
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

    fun isLoading(): LiveData<Boolean> {
        return _isLoading
    }

    fun getDeals(): MutableLiveData<Product> {
        return _dealDetails
    }

    private fun retrieveProductDetails(dealId: Int) {
        _isLoading.value = true
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.target.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ProductDetailsService::class.java)
        val products = service.retrieveProductDetails(dealId)
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