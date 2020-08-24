package com.nikolam.orders.data.network

import com.google.firebase.firestore.FirebaseFirestore
import com.nikolam.core.model.MenuItem
import com.nikolam.orders.data.model.Order
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import timber.log.Timber

class FirebaseDataSource(private val firebaseFirestore: FirebaseFirestore) : NetworkDataSource {

    @ExperimentalCoroutinesApi
    override fun fetchOrders(): Flow<Order> = callbackFlow {
        firebaseFirestore.collection("orders")
            .get()
            .addOnSuccessListener { documents ->
                try {
                    for (document in documents) {
                        val doc = document.toObject(Order::class.java)
                        doc.orderID = document.id
                        Timber.d(doc.toString())
                        offer(doc)
                    }
                } catch (e: Exception) {
                    Timber.d("Error getting documents:  ${e.localizedMessage}")
                }
            }
            .addOnFailureListener { exception ->
                Timber.d("Failure getting documents:  ${exception.localizedMessage}")
                close(exception)
            }
            .addOnCompleteListener {
                close()
            }
        awaitClose { Timber.d("Closed") }
    }


}
