package com.nikolam.menu.data

import com.nikolam.core.model.MenuItem
import kotlinx.coroutines.flow.Flow

class FakeMenuRepository : IMenuRepository{
    override fun fetchMenuItems(): Flow<MenuItem> {
        TODO("Not yet implemented")
    }

    override fun fetchMenuItem(itemID: String): Flow<MenuItem> {
        TODO("Not yet implemented")
    }

    override fun updateMenuItem(itemID: String, updatedItem: MenuItem) {
        TODO("Not yet implemented")
    }

}