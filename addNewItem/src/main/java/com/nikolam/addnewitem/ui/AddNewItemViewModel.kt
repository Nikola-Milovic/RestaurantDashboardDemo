package com.nikolam.addnewitem.ui

import androidx.lifecycle.ViewModel
import com.nikolam.addnewitem.data.IAddNewItemRepository
import com.nikolam.core.model.MenuItem

class AddNewItemViewModel(private val repository: IAddNewItemRepository) : ViewModel() {


    fun addFoodItem(menuItem: MenuItem) {
        repository.addFoodItem(menuItem)
    }

}
