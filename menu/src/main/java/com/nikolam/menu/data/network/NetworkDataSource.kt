package com.nikolam.menu.data.network

import com.nikolam.core.model.Food
import kotlinx.coroutines.flow.Flow

interface NetworkDataSource {

    fun fetchMenuItems() : Flow<Food>

}