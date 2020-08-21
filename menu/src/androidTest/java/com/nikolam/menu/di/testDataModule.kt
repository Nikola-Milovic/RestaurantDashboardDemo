package com.nikolam.menu.di

import com.nikolam.menu.data.FakeMenuRepository
import com.nikolam.menu.data.IMenuRepository
import org.koin.dsl.module

val testDataModule = module(override = true){
    single<IMenuRepository>(override = true){ FakeMenuRepository() }
}