package com.nikolam.menu.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nikolam.menu.R
import com.nikolam.core.model.Food
import com.nikolam.menu.databinding.MenuItemBinding
import com.nikolam.core.utils.bindings


class MenuAdapter() :
    RecyclerView.Adapter<MenuAdapter.FoodViewHolder>() {

    private var menuItems : ArrayList<Food> = arrayListOf()

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FoodViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.menu_item, parent, false)
        return FoodViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val data = menuItems[position]
        try {
            holder.bindData(data)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun addMenuItems(menuItemsList : ArrayList<Food>) {
        menuItems.clear()
        menuItems.addAll(menuItemsList)
        this.notifyDataSetChanged()
    }

    override fun getItemCount() : Int {
        return menuItems.size
    }


    inner class FoodViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private lateinit var data: Food
        private val binding: MenuItemBinding by bindings(view)

        fun bindData(data: Any) {
            if (data is Food) {
                this.data = data
                drawItemUI()
            }
        }

        private fun drawItemUI() {
            binding.apply {
                food = data
                executePendingBindings()
            }
        }

    }

}
