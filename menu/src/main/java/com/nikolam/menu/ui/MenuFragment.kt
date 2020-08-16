package com.nikolam.menu.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikolam.menu.R
import com.nikolam.menu.databinding.MenuFragmentBinding
import com.nikolam.menu.ui.adapter.MenuAdapter
import com.nikolam.menu.utils.DatabindingFragment
import org.koin.android.ext.android.inject
import timber.log.Timber

class MenuFragment : DatabindingFragment() {

    lateinit var binding : MenuFragmentBinding

    lateinit var menuAdapter : MenuAdapter

    private val viewModel: MenuViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding<MenuFragmentBinding>(inflater, R.layout.menu_fragment, container).apply {

            val layoutMana=
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

            menuAdapter = MenuAdapter()
            recyclerViews.apply{
                layoutManager = layoutMana
            }
            this.adapter = menuAdapter
            viewModel = viewModel
            lifecycleOwner = this@MenuFragment
        }.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.fetchMenuItems()
        observeData()
    }

    private fun observeData(){
        viewModel._menuItemsLiveData.observe(viewLifecycleOwner, Observer {
            menuAdapter.addMenuItems(it)
        })
    }

}