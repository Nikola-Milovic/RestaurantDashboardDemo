package com.nikolam.menu.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikolam.menu.data.IMenuRepository
import com.nikolam.menu.data.model.Food
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import timber.log.Timber

class MenuViewModel(private val repository : IMenuRepository) : ViewModel() {

    val menuItemsLiveData : MutableLiveData<ArrayList<Food>> = MutableLiveData()
    val _menuItemsLiveData : LiveData<ArrayList<Food>>
        get() = menuItemsLiveData



    fun fetchMenuItems() {
        var foodList = arrayListOf<Food>()
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchMenuItems().collect{ value ->
                Timber.d(value.toString())
                foodList.add(value)
            }

            menuItemsLiveData.postValue(foodList)
        }
    }
}