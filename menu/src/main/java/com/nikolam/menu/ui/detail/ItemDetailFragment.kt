package com.nikolam.menu.ui.detail

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nikolam.core.model.MenuItem
import com.nikolam.menu.R
import com.nikolam.menu.databinding.ItemDetailFragmentBinding
import com.nikolam.menu.di.dataModule
import com.nikolam.menu.di.viewmodelModule
import com.nikolam.menu.ui.detail.adapter.OptionsDetailAdapter
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import timber.log.Timber


class ItemDetailFragment : Fragment() {

    //Koin
    val moduleList = arrayListOf(dataModule, viewmodelModule)

    private val loadModules by lazy {
        loadKoinModules(moduleList)
    }

    private fun injectFeatures() = loadModules


    //
    lateinit var optionsAdapter : OptionsDetailAdapter

    lateinit var binding : ItemDetailFragmentBinding

    lateinit var itemID : String

    private val detailViewModel: ItemDetailViewModel by inject()

    val args : ItemDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.item_detail_fragment,container, false)

        val view = binding.root

        optionsAdapter = OptionsDetailAdapter()

        val layoutM=
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        binding.apply {
            optionsRecycleView.layoutManager = layoutM
            this.adapter = optionsAdapter
            this.viewModel = detailViewModel
            lifecycleOwner = this@ItemDetailFragment
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        detailViewModel.fetchMenuItem(args.MenuItemID)
    }

    private fun observeData(){
        binding.saveButtonDetail.setOnClickListener {
            val item = MenuItem()

            optionsAdapter

            item.name = binding.nameTextInputDetail.text.toString()
            item.prices

            detailViewModel.updateItem(itemID, item)
        }
        detailViewModel._itemLiveData.observe(viewLifecycleOwner, Observer {
           binding.item = it
            itemID = it.itemID

            optionsAdapter.addPriceOptions(it.prices)

            Timber.d(it.toString())
        })
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