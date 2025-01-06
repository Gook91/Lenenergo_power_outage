package com.gbl.lenenergopoweroutage.data.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gbl.lenenergopoweroutage.data.local.db.AddressFilterDBModel.Companion.ADDRESS_FILTER_TABLE_NAME

@Entity(tableName = ADDRESS_FILTER_TABLE_NAME)
data class AddressFilterDBModel(
    @PrimaryKey
    val address: String
) {
    companion object {
        const val ADDRESS_FILTER_TABLE_NAME = "address_filter_table"
    }
}
