package com.nikolam.orders.data.model

data class Order(var orderItems : ArrayList<String> = arrayListOf(), var tableNumber : String = "", var orderID : String = "")