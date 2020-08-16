package com.nikolam.menu.data

import com.nikolam.core.model.Food
import com.nikolam.menu.data.network.NetworkDataSource
import kotlinx.coroutines.flow.Flow

class ImplMenuRepository(private val firebaseDataSource : NetworkDataSource): IMenuRepository {

    override fun fetchMenuItems(): Flow<Food> {
        return firebaseDataSource.fetchMenuItems()
    }

}