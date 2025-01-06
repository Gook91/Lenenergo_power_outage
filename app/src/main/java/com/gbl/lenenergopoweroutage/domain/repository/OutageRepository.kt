package com.gbl.lenenergopoweroutage.domain.repository

import com.gbl.lenenergopoweroutage.domain.model.DataState
import com.gbl.lenenergopoweroutage.domain.model.Outage
import kotlinx.coroutines.flow.Flow

interface OutageRepository {
    fun getOutages(addresses: List<String>, date: Long?): Flow<DataState<List<Outage>>>
}