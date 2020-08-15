package com.nikolam.menu.data.network

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.nikolam.menu.data.model.Food
import com.nikolam.menu.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber

class FirebaseDataSource (private val firebaseFirestore: FirebaseFirestore) : NetworkDataSource{

    fun fetchMenuItems(): Flow<Food> = flow{
        firebaseFirestore.collection("menu_items")
                .get()
                .addOnSuccessListener { documents ->
                    try {
                        for (document in documents) {
                            val doc = document.toObject(Food::class.java)
                            emit(doc)
                        }
                    } catch (e: Exception) {
                        //emit(Result.Failed(e))
                        Timber.d("Error getting documents:  ${e.localizedMessage}")
                    }
                }
                .addOnFailureListener { exception ->
                    Timber.d("Error getting documents:  ${exception.localizedMessage}")
                }

    }
}


