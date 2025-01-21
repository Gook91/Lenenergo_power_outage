package com.gbl.lenenergopoweroutage.ui.screen.main

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.ConcatAdapter
import com.gbl.lenenergopoweroutage.R
import com.gbl.lenenergopoweroutage.databinding.ActivityMainBinding
import com.gbl.lenenergopoweroutage.databinding.BodyActivityMainBinding
import com.gbl.lenenergopoweroutage.databinding.TopAppBarBinding
import com.gbl.lenenergopoweroutage.domain.model.DataState
import com.gbl.lenenergopoweroutage.ui.list.adapter.AddressInFilterAdapter
import com.gbl.lenenergopoweroutage.ui.list.adapter.HeaderAddressInFilterAdapter
import com.gbl.lenenergopoweroutage.ui.list.adapter.OutageAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: ActivityViewModel by viewModel()

    private var mainBinding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding?.root)
        ViewCompat.setOnApplyWindowInsetsListener(mainBinding?.root as View) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mainBinding?.topAppBar?.let { createTopBar(it) }
        mainBinding?.bodyActivityMain?.let { createBody(it) }
    }

    private fun createTopBar(binding: TopAppBarBinding) = with(binding) {
        val headerAddressAdapter =
            HeaderAddressInFilterAdapter { viewModel.addAddressFilter(it) }
        val addressInFilterAdapter =
            AddressInFilterAdapter { viewModel.deleteAddressFilter(it) }
        addressFilterList.adapter = ConcatAdapter(headerAddressAdapter, addressInFilterAdapter)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.addressFilterFlow.collect {
                    addressInFilterAdapter.submitList(it)
                }
            }
        }
        expandSettings.setOnCheckedChangeListener { _, isChecked ->
            expandableGroup.visibility = if (isChecked)
                View.VISIBLE
            else
                View.GONE
        }
    }

    private fun createBody(binding: BodyActivityMainBinding) = with(binding) {
        val outageAdapter = OutageAdapter()
        outageList.adapter = outageAdapter

        refreshButton.setOnClickListener { viewModel.refresh() }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.outageFlow.collect { dataState ->
                    when (dataState) {
                        is DataState.Cached -> {
                            notFoundOutagesPlaceholder.isVisible = dataState.data.isEmpty()
                            outageAdapter.data = dataState.data
                        }

                        is DataState.Online -> {
                            notFoundOutagesPlaceholder.isVisible = dataState.data.isEmpty()
                            outageAdapter.data = dataState.data
                        }

                        is DataState.Error -> {
                            Snackbar.make(
                                binding.root,
                                getString(R.string.loading_error),
                                Snackbar.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainBinding = null
    }
}