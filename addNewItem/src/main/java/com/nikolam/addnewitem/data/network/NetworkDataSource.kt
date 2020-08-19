package com.nikolam.addnewitem.data.network

import com.nikolam.core.model.MenuItem

interface NetworkDataSource {
    fun saveFoodItem(menuItem: MenuItem)
}
