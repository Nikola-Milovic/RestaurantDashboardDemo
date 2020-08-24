package com.nikolam.orders.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikolam.core.model.MenuItem
import com.nikolam.orders.data.IMenuRepository
import com.nikolam.orders.data.model.Order
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

class OrderViewModel(private val repository: IMenuRepository) : ViewModel() {

    open val ordersItemsLiveData: MutableLiveData<ArrayList<Order>> = MutableLiveData()
    open val _ordersItemsLiveData: LiveData<ArrayList<Order>>
        get() = ordersItemsLiveData


    fun fetchOrders(){

        val orderList = arrayListOf<Order>()
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchOrders().collect { value ->
                Timber.d(value.toString())
                orderList.add(value)
            }

            ordersItemsLiveData.postValue(orderList)
        }

    }


}