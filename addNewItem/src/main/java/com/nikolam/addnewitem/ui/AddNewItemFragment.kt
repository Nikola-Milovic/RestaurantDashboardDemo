package com.nikolam.addnewitem.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.textfield.TextInputEditText
import com.nikolam.addnewitem.R
import com.nikolam.addnewitem.databinding.AddNewItemFragmentBinding
import com.nikolam.addnewitem.di.dataModule
import com.nikolam.addnewitem.di.viewmodelModule
import com.nikolam.addnewitem.ui.adapter.OptionsAdapter
import com.nikolam.core.model.MenuItem
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class AddNewItemFragment : Fragment() {

    //Koin
    val moduleList = arrayListOf(dataModule, viewmodelModule)

    private val loadModules by lazy {
        loadKoinModules(moduleList)
    }

    private fun injectFeatures() = loadModules


    lateinit var optionsAdapter: OptionsAdapter
    lateinit var binding: AddNewItemFragmentBinding

    private val viewModel: AddNewItemViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.add_new_item_fragment, container, false)
        binding.apply {

            val layoutMana =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

            optionsAdapter = OptionsAdapter()
            optionsRecycleView.apply {
                layoutManager = layoutMana
            }
            this.adapter = optionsAdapter
            viewModel = viewModel
            lifecycleOwner = this@AddNewItemFragment
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveButton.setOnClickListener {

            val menuItem: MenuItem = MenuItem()

            menuItem.name =
                view.findViewById<TextInputEditText>(R.id.name_textInput).text.toString()
            menuItem.prices = optionsAdapter.getPrices()

            viewModel.addFoodItem(menuItem)
        }

        binding.addOptionActionButton.setOnClickListener {
            optionsAdapter.addNewOption()
        }

    }

    // Loading unloading modules
    override fun onAttach(context: Context) {
        super.onAttach(context)

        injectFeatures()
    }

    override fun onDetach() {
        unloadKoinModules(moduleList)
        super.onDetach()
    }
}
