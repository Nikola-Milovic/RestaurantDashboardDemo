package com.nikolam.menu.data.network

import com.nikolam.menu.data.model.Food
import kotlinx.coroutines.flow.Flow

interface NetworkDataSource {

    fun fetchMenuItems() : Flow<Food>

}