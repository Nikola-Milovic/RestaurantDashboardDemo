package com.nikolam.menu.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nikolam.menu.R
import org.koin.android.ext.android.inject
import timber.log.Timber

class MenuFragment : Fragment() {

    companion object {
        fun newInstance() = MenuFragment()
    }

    private val viewModel: MenuViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel.fetchMenuItems()

        return inflater.inflate(R.layout.menu_fragment, container, false)
    }
}