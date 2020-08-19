package com.nikolam.addnewitem.data

import com.nikolam.addnewitem.data.network.NetworkDataSource
import com.nikolam.core.model.MenuItem

class ImplAddNewItemRepository(private val firebaseDataSource: NetworkDataSource) :
    IAddNewItemRepository {
    override fun addFoodItem(menuItem: MenuItem) {
        firebaseDataSource.saveFoodItem(menuItem)
    }

}
