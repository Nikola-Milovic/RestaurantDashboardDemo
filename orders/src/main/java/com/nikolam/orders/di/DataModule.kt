package com.nikolam.orders.di


import com.nikolam.orders.data.IMenuRepository
import com.nikolam.orders.data.ImplMenuRepository
import com.nikolam.orders.data.network.FirebaseDataSource
import com.nikolam.orders.data.network.NetworkDataSource
import org.koin.dsl.module

val dataModule = module {

    single<IMenuRepository> { ImplMenuRepository(get()) }

    single<NetworkDataSource> { FirebaseDataSource(get()) }

}
