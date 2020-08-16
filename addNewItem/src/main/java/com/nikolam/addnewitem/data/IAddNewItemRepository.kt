package com.nikolam.addnewitem.data

import com.nikolam.core.model.Food

interface IAddNewItemRepository {
    fun addFoodItem(food: Food)
}