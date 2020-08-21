package com.nikolam.menu.di

import com.nikolam.menu.ui.menu.FakeMenuViewModel
import com.nikolam.menu.ui.menu.MenuViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val testViewModelModule = module(override = true) {

    viewModel<MenuViewModel> { FakeMenuViewModel(get()) }

}