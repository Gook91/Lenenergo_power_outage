package com.gbl.lenenergopoweroutage.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.gbl.lenenergopoweroutage.data.local.db.OutageDBModel.Companion.OUTAGE_TABLE_NAME

@Dao
interface OutageDao {
    @Insert
    suspend fun insertAll(outages: List<OutageDBModel>)

    @Query("DELETE FROM $OUTAGE_TABLE_NAME")
    suspend fun clearTable()

    @Transaction
    suspend fun rewriteData(outageList: List<OutageDBModel>) {
        clearTable()
        insertAll(outageList)
    }

    @Query("SELECT * FROM $OUTAGE_TABLE_NAME")
    suspend fun getAll(): List<OutageDBModel>
}