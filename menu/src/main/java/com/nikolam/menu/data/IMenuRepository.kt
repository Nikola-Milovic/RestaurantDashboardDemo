package com.nikolam.menu.data

import com.nikolam.core.model.Food
import kotlinx.coroutines.flow.Flow

interface IMenuRepository {

    fun fetchMenuItems() : Flow<Food>

}