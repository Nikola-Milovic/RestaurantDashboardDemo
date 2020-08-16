package com.nikolam.addnewitem.ui

import androidx.lifecycle.ViewModel
import com.nikolam.addnewitem.data.IAddNewItemRepository
import com.nikolam.core.model.Food

class AddNewItemViewModel(private val repository: IAddNewItemRepository) : ViewModel() {


    fun addFoodItem(food: Food) {
        repository.addFoodItem(food)
    }

}