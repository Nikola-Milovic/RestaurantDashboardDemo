package com.nikolam.addnewitem.data

import com.nikolam.addnewitem.data.network.NetworkDataSource
import com.nikolam.core.model.Food

class ImplAddNewItemRepository(private val firebaseDataSource : NetworkDataSource) : IAddNewItemRepository {
    override fun addFoodItem(food: Food) {
        firebaseDataSource.saveFoodItem(food)
    }

}