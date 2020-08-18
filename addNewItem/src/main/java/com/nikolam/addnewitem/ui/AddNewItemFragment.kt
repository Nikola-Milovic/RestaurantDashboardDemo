package com.nikolam.addnewitem.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.nikolam.addnewitem.R
import com.nikolam.addnewitem.di.dataModule
import com.nikolam.addnewitem.di.viewmodelModule
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




    private val viewModel: AddNewItemViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_new_item_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<MaterialButton>(R.id.save_button).setOnClickListener {

            val menuItem : MenuItem = MenuItem()

            menuItem.name = view.findViewById<TextInputEditText>(R.id.name_textInput).text.toString()
          //  menuItem.price = view.findViewById<TextInputEditText>(R.id.price_textInput).text.toString().toInt()

            viewModel.addFoodItem(menuItem)
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