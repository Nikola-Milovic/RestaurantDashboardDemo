package com.nikolam.menu.data.network

import com.google.firebase.firestore.FirebaseFirestore
import com.nikolam.core.model.Food
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import timber.log.Timber

class FirebaseDataSource (private val firebaseFirestore: FirebaseFirestore) : NetworkDataSource{

    @ExperimentalCoroutinesApi
    override fun fetchMenuItems(): Flow<Food> = callbackFlow{
        firebaseFirestore.collection("menu_items")
                .get()
                .addOnSuccessListener { documents ->
                    try {
                        for (document in documents) {
                            val doc = document.toObject(Food::class.java)
                            doc.foodID = document.id
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


