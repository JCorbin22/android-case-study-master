package com.target.targetcasestudy.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.ClassCastException

/**
 * A ViewModel factory that provides an instance of the ProductDetailsViewModel with a parameterized
 * constructor, allowing a product ID to be passed to the ViewModel in order to call the service.
 */
class ProductViewModelFactory(private val productId: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        try {
            return ProductDetailsViewModel(productId) as T
        } catch (e: ClassCastException) {
            Log.e(this.javaClass.name, "Could not cast ViewModel")
            throw e
        }
    }
}