package com.nikolam.addnewitem.data.network

import com.nikolam.core.model.Food

interface NetworkDataSource {
    fun saveFoodItem(food : Food)
}