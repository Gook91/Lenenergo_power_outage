package com.gbl.lenenergopoweroutage.data.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gbl.lenenergopoweroutage.data.local.db.OutageDBModel.Companion.OUTAGE_TABLE_NAME
import com.gbl.lenenergopoweroutage.domain.model.Outage

@Entity(tableName = OUTAGE_TABLE_NAME)
data class OutageDBModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val region: String,
    val district: String,
    val addresses: String,
    val startDate: Long,
    val endDate: Long,
    val res: String,
    val commentary: String,
) {
    constructor(outage: Outage) : this(
        region = outage.region,
        district = outage.district,
        addresses = outage.addresses,
        startDate = outage.startDate,
        endDate = outage.endDate,
        res = outage.res,
        commentary = outage.commentary,
    )

    fun toOutage(): Outage = Outage(
        region = region,
        district = district,
        addresses = addresses,
        startDate = startDate,
        endDate = endDate,
        res = res,
        commentary = commentary
    )

    companion object {
        const val OUTAGE_TABLE_NAME = "outage_table"
    }
}
