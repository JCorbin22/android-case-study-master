package com.target.targetcasestudy.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.target.targetcasestudy.data.DealItem

class DealListViewModel : ViewModel() {

    private lateinit var _isLoading: MutableLiveData<Boolean>
    private lateinit var _dealsList: MutableLiveData<List<DealItem>>

    fun isLoading(): MutableLiveData<Boolean> {
        if(_isLoading == null) {
            _isLoading = MutableLiveData<Boolean>()
            _isLoading.value = true
        }
        return _isLoading
    }

    fun getDeals(): MutableLiveData<List<DealItem>> {
        // fetch the deals! Implement retrofit!
        return _dealsList
    }
}