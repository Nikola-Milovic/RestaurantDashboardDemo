package com.nikolam.orders.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nikolam.orders.R
import com.nikolam.orders.data.model.Order
import timber.log.Timber


class OrderAdapter() :
    RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    private var orderItems : ArrayList<Order> = arrayListOf()

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.order_item, parent, false)
        return OrderViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val data = orderItems[position]
        try {
            holder.bindData(data)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun addOrderItems(orderItemsList : ArrayList<Order>) {
        orderItems.clear()
        orderItems.addAll(orderItemsList)
        this.notifyDataSetChanged()
    }

    override fun getItemCount() : Int {
        return orderItems.size
    }


    inner class OrderViewHolder(val view: View) : RecyclerView.ViewHolder(view) {


        fun bindData(data: Any) {
            if(data is Order){
                var text = ""
                (data as Order).orderItems.forEach {
                  text = text + it + "\n"
                }
                view.findViewById<TextView>(R.id.orderText_textView).text = text
            }


        }
    }

}
