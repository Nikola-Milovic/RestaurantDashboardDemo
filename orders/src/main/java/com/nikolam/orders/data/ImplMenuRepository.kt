package com.nikolam.orders.data

import com.nikolam.core.model.MenuItem
import com.nikolam.orders.data.model.Order
import com.nikolam.orders.data.network.NetworkDataSource
import kotlinx.coroutines.flow.Flow

class ImplMenuRepository(private val firebaseDataSource: NetworkDataSource) : IMenuRepository {

    override fun fetchOrders(): Flow<Order> {
        return firebaseDataSource.fetchOrders()
    }

}
