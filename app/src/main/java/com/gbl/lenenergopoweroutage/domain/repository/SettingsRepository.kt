package com.gbl.lenenergopoweroutage.domain.repository

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    fun getAddressFilter(): Flow<List<String>>

    suspend fun addAddressFilter(address: String)

    suspend fun deleteAddressFilter(address: String)
}