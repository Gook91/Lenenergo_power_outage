package com.gbl.lenenergopoweroutage.data.local

import com.gbl.lenenergopoweroutage.data.local.db.AddressFilterDBModel
import com.gbl.lenenergopoweroutage.data.local.db.AddressFilterDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AddressFilterLocalDataSource(
    private val addressFilterDao: AddressFilterDao
) {
    suspend fun addAddressFilter(address: String) =
        addressFilterDao.addAddress(AddressFilterDBModel(address))

    suspend fun deleteAddressFilter(address: String) =
        addressFilterDao.deleteAddress(AddressFilterDBModel(address))

    fun getAllAddressFilters(): Flow<List<String>> =
        addressFilterDao.getAllAddresses().map { addressList -> addressList.map { it.address } }
}