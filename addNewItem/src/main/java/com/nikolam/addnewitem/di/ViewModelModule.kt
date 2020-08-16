package com.nikolam.addnewitem.di


import com.nikolam.addnewitem.ui.AddNewItemViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewmodelModule = module {

    viewModel{AddNewItemViewModel(get())}

}