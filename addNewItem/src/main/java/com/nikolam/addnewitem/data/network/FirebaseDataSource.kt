package com.nikolam.addnewitem.data.network

import com.google.firebase.firestore.FirebaseFirestore
import com.nikolam.core.model.Food
import timber.log.Timber

class FirebaseDataSource(private val firebaseFirestore: FirebaseFirestore)  : NetworkDataSource{
    override fun saveFoodItem(food: Food) {
        val foodToSave = hashMapOf(
            "name" to food.name,
            "price" to food.price
        )


        firebaseFirestore.collection("menu_items").add(foodToSave)
            .addOnSuccessListener { Timber.d("DocumentSnapshot successfully written!") }
            .addOnFailureListener { e ->Timber.d("Error writing document ${e.localizedMessage}") }
    }

}