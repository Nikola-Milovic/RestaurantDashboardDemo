package com.nikolam.menu.di

import com.nikolam.menu.ui.MenuViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewmodelModule = module {

    viewModel{MenuViewModel(get())}

}