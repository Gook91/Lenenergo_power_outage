package com.gbl.lenenergopoweroutage.data

import com.gbl.lenenergopoweroutage.data.local.AddressFilterLocalDataSource
import com.gbl.lenenergopoweroutage.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow

class SettingsRepositoryImpl(
    private val addressFilterLocalDataSource: AddressFilterLocalDataSource
) : SettingsRepository {
    override fun getAddressFilter(): Flow<List<String>> =
        addressFilterLocalDataSource.getAllAddressFilters()

    override suspend fun addAddressFilter(address: String) =
        addressFilterLocalDataSource.addAddressFilter(address)

    override suspend fun deleteAddressFilter(address: String) =
        addressFilterLocalDataSource.deleteAddressFilter(address)
}