package com.nikolam.menu.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikolam.menu.data.IMenuRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import timber.log.Timber

class MenuViewModel(private val repository : IMenuRepository) : ViewModel() {

    fun fetchMenuItems() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchMenuItems().collect{ value ->
                Timber.d(value.toString())
            }
        }
    }
}