package com.nikolam.menu.ui.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nikolam.core.model.MenuItem
import com.nikolam.core.model.Price
import com.nikolam.menu.data.IMenuRepository

class FakeMenuViewModel(repository: IMenuRepository) : MenuViewModel(repository) {

    override val menuItemsLiveData: MutableLiveData<ArrayList<MenuItem>> = MutableLiveData()
    override val _menuItemsLiveData: LiveData<ArrayList<MenuItem>>
        get() = menuItemsLiveData


    override fun fetchMenuItems() {

        menuItemsLiveData.value = arrayListOf(MenuItem("X", arrayListOf(Price("X", 50))),
            MenuItem("Y", arrayListOf(Price("Y", 150))),
            MenuItem("Z", arrayListOf(Price("Z", 250))))

    }

}