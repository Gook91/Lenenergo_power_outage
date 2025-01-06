package com.gbl.lenenergopoweroutage.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.gbl.lenenergopoweroutage.data.local.db.AddressFilterDBModel.Companion.ADDRESS_FILTER_TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface AddressFilterDao {
    @Upsert
    suspend fun addAddress(address: String)

    @Delete
    suspend fun deleteAddress(address: String)

    @Query("SELECT * FROM $ADDRESS_FILTER_TABLE_NAME")
    fun getAllAddresses(): Flow<List<String>>
}