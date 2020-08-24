package com.nikolam.orders.data

import com.nikolam.core.model.MenuItem
import com.nikolam.orders.data.model.Order
import kotlinx.coroutines.flow.Flow

interface IMenuRepository {

    fun fetchOrders(): Flow<Order>

}
