package com.gbl.lenenergopoweroutage.ui.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gbl.lenenergopoweroutage.domain.model.DataState
import com.gbl.lenenergopoweroutage.domain.useCase.AddAddressFilterUseCase
import com.gbl.lenenergopoweroutage.domain.useCase.GetAddressFilterUseCase
import com.gbl.lenenergopoweroutage.domain.useCase.GetOutagesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ActivityViewModel(
    private val getOutagesUseCase: GetOutagesUseCase,
    private val addAddressFilterUseCase: AddAddressFilterUseCase,
    private val deleteFilterUseCase: AddAddressFilterUseCase,
    getAddressFilterUseCase: GetAddressFilterUseCase,
) : ViewModel() {

    // Outages unit

    private val refreshTrigger = MutableSharedFlow<Unit>()

    fun refresh() {
        viewModelScope.launch {
            refreshTrigger.emit(Unit)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val outageFlow = refreshTrigger.flatMapLatest { getOutagesUseCase.invoke() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000L),
            DataState.Cached(emptyList())
        )

    // Address filter unit

    val addressFilterFlow = getAddressFilterUseCase.invoke().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000L),
        emptyList()
    )

    fun addAddressFilter(address: String) {
        viewModelScope.launch {
            addAddressFilterUseCase.invoke(address)
        }
    }

    fun deleteAddressFilter(address: String) {
        viewModelScope.launch {
            deleteFilterUseCase.invoke(address)
        }
    }
}