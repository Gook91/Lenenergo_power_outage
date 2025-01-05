package com.gbl.lenenergopoweroutage.data.local

import com.gbl.lenenergopoweroutage.data.local.db.OutageDBModel
import com.gbl.lenenergopoweroutage.data.local.db.OutageDao
import com.gbl.lenenergopoweroutage.domain.model.Outage

class OutageLocalDataSource(
    private val outageDao: OutageDao
) {
    suspend fun addOutages(outageList: List<Outage>) =
        outageDao.rewriteData(outageList.map { OutageDBModel(it) })

    suspend fun getOutages(): List<Outage> =
        outageDao.getAll().map { it.toOutage() }
}