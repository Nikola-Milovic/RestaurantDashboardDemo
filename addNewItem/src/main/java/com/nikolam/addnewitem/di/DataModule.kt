package com.nikolam.addnewitem.di

import com.google.firebase.firestore.FirebaseFirestore
import com.nikolam.addnewitem.data.IAddNewItemRepository
import com.nikolam.addnewitem.data.ImplAddNewItemRepository
import com.nikolam.addnewitem.data.network.FirebaseDataSource
import com.nikolam.addnewitem.data.network.NetworkDataSource
import org.koin.dsl.module

val dataModule = module {

    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    single { provideFirebaseFirestore() }

    single<IAddNewItemRepository>{ImplAddNewItemRepository(get())}

    single<NetworkDataSource>{ FirebaseDataSource(get()) }

}