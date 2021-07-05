package com.target.targetcasestudy.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.ClassCastException

class ProductViewModelFactory(private val dealItem: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        try {
            return ProductDetailsViewModel(dealItem) as T
        } catch (e: ClassCastException) {
            Log.e(this.javaClass.name, "Could not cast ViewModel")
            throw e
        }
    }
}