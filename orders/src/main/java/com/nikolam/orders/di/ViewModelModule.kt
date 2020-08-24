package com.nikolam.orders.di

import com.nikolam.orders.ui.OrderViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewmodelModule = module {

    viewModel { OrderViewModel(get()) }

}
