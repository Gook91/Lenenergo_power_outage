package com.gbl.lenenergopoweroutage.data.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gbl.lenenergopoweroutage.data.local.db.OutageDBModel.Companion.OUTAGE_TABLE_NAME
import com.gbl.lenenergopoweroutage.domain.model.Outage

@Entity(tableName = OUTAGE_TABLE_NAME)
data class OutageDBModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val addresses: String,
    val startDate: Long,
    val endDate: Long,
    val commentary: String,
) {
    constructor(outage: Outage) : this(
        addresses = outage.addresses,
        startDate = outage.startDate,
        endDate = outage.endDate,
        commentary = outage.commentary,
    )

    fun toOutage(): Outage = Outage(
        addresses = addresses,
        startDate = startDate,
        endDate = endDate,
        commentary = commentary
    )

    companion object {
        const val OUTAGE_TABLE_NAME = "outage_table"
    }
}
