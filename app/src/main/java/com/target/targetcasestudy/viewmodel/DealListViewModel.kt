package com.target.targetcasestudy.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.target.targetcasestudy.model.Product
import com.target.targetcasestudy.model.Products
import com.target.targetcasestudy.model.ProductsResponse
import com.target.targetcasestudy.services.ProductsService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DealListViewModel : ViewModel() {

    private var _isLoading = MutableLiveData<Boolean>().apply { value = false }
    private val _dealsList: MutableLiveData<Products> by lazy {
        MutableLiveData<Products>().also {
            fetchDeals()
        }
    }

    fun isLoading(): LiveData<Boolean> {
        return _isLoading
    }

    fun getDeals(): MutableLiveData<Products> {
        // fetch the deals! Implement retrofit!
        return _dealsList
    }

    private fun fetchDeals() {
        _isLoading.value = true
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.target.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ProductsService::class.java)
        val products = service.listProducts()
        products.enqueue(object : Callback<Products> {
            override fun onResponse(
                call: Call<Products>,
                response: Response<Products>
            ) {
                if(response.isSuccessful) {
                    _dealsList.value = response.body()
                    _isLoading.value = false
                }
            }

            override fun onFailure(call: Call<Products>, t: Throwable) {
                // todo cancel the progress bar and then show a toast
                Log.e("UH OH!", t.message.toString())
            }
        })
    }
}