package com.nikolam.menu.ui.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nikolam.menu.R
import com.nikolam.core.model.Price
import com.nikolam.core.utils.bindings
import com.nikolam.menu.databinding.OptionsItemDetailBinding


class OptionsDetailAdapter() :
    RecyclerView.Adapter<OptionsDetailAdapter.OptionsDetailViewHolder>() {

    private var prices : ArrayList<Price> = arrayListOf()

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OptionsDetailViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.options_item_detail, parent, false)
        return OptionsDetailViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holderDetail: OptionsDetailViewHolder, position: Int) {
        val data = prices[position]
        try {
            holderDetail.bindData(data)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun addPriceOptions(priceOptionList : ArrayList<Price>) {
        prices.clear()
        prices.addAll(priceOptionList)
        this.notifyDataSetChanged()
    }

//    fun getPrices() : ArrayList<Price>{
//
//  //      for price
//
//    }

    override fun getItemCount() : Int {
        return prices.size
    }


    inner class OptionsDetailViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private lateinit var data: Price
        private val binding: OptionsItemDetailBinding by bindings(view)

        fun bindData(data: Any) {
            if (data is Price) {
                this.data = data
                drawItemUI()
            }
        }

        private fun drawItemUI() {
            binding.apply {
                price = data
                executePendingBindings()
            }
        }
    }
}
