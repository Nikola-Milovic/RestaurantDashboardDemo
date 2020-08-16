package com.nikolam.addnewitem.data

import com.nikolam.core.model.MenuItem

interface IAddNewItemRepository {
    fun addFoodItem(menuItem: MenuItem)
}