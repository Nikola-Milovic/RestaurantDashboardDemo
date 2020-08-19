package com.nikolam.menu.ui.detail.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nikolam.menu.R
import com.nikolam.core.model.Price
import com.nikolam.core.utils.bindings
import com.nikolam.menu.databinding.OptionsItemDetailBinding
import timber.log.Timber


class OptionsDetailAdapter() :
    RecyclerView.Adapter<OptionsDetailAdapter.OptionsDetailViewHolder>(), DataChangeListener {

    private var prices : ArrayList<Price> = arrayListOf()

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OptionsDetailViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.options_item_detail, parent, false)
        return OptionsDetailViewHolder(view, this)
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


    override fun getItemCount() : Int {
        return prices.size
    }


    fun getPrices() : ArrayList<Price>{
        Timber.d("prices are $prices")
        return prices
    }

    override fun updatePrice(option: String, newPrice: Int) {
        for (i in 0 until prices.size){
            if (prices[i].option == option){
                prices[i].price = newPrice
            }
        }
    }

    override fun updateOption(newOption: String, oldOption: String) {
        for (i in 0 until prices.size){
            if (prices[i].option == oldOption){
                prices[i].option = newOption
            }
        }
    }

    override fun deletePrice(price: Price) {
        if (prices.contains(price)){
            prices.remove(price)
            this.notifyDataSetChanged()
        }
    }

    fun addNewOption(){
        val price = Price("Promeniti", 0 )
        while(prices.contains(price)) {
            val rnds = (0..100).random()
            price.option = "Promeniti$rnds"
        }
        prices.add(price)
        this.notifyDataSetChanged()
    }

    inner class OptionsDetailViewHolder(val view: View, val listener : DataChangeListener) : RecyclerView.ViewHolder(view) {

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

                optionInputText.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {
                        listener.updateOption(s.toString(), data.option)
                        data.option = s.toString()

                        price = data
                    }

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int
                    ) {
                       //
                    }
                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int
                    ) {
                       //listener.changePriceOption(Price(optionInputText.text.toString(), priceInputText.text.toString().toInt()))
                    }

                })

                priceInputText.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {
                        try{
                        listener.updatePrice(data.option, s.toString().toInt())
                        data.price = s.toString().toInt()
                        } catch (e : Exception){
                            listener.updatePrice(data.option, 0)
                            data.price = 0
                        }

                        price = data
                    }

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int
                    ) {
                        //
                    }
                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int ){
                      //
                    }

                })

                deleteActionButton.setOnClickListener {

                    listener.deletePrice(data)

                }
            }
        }
    }

}


interface DataChangeListener {
    fun updatePrice(option : String, newPrice : Int)

    fun updateOption(newOption : String, oldOption : String)

    fun deletePrice(price : Price)
}