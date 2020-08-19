package com.nikolam.addnewitem.data.network

import com.google.firebase.firestore.FirebaseFirestore
import com.nikolam.core.model.MenuItem
import timber.log.Timber

class FirebaseDataSource(private val firebaseFirestore: FirebaseFirestore) : NetworkDataSource {

    override fun saveFoodItem(menuItem: MenuItem) {
        val foodToSave = hashMapOf(
            "name" to menuItem.name,
            "prices" to menuItem.prices
        )


        firebaseFirestore.collection("menu_items").add(foodToSave)
            .addOnSuccessListener { Timber.d("DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Timber.d("Error writing document ${e.localizedMessage}") }
    }

}
