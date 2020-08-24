package com.nikolam.orders.data.network

import com.nikolam.core.model.MenuItem
import com.nikolam.orders.data.model.Order
import kotlinx.coroutines.flow.Flow

interface NetworkDataSource {

    fun fetchOrders(): Flow<Order>

}
