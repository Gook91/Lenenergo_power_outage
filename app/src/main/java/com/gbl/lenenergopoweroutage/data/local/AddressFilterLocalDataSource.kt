package com.gbl.lenenergopoweroutage.data.local

import com.gbl.lenenergopoweroutage.data.local.db.AddressFilterDao
import kotlinx.coroutines.flow.Flow

class AddressFilterLocalDataSource(
    private val addressFilterDao: AddressFilterDao
) {
    suspend fun addAddressFilter(address: String) = addressFilterDao.addAddress(address)

    suspend fun deleteAddressFilter(address: String) = addressFilterDao.deleteAddress(address)

    fun getAllAddressFilters(): Flow<List<String>> = addressFilterDao.getAllAddresses()
}