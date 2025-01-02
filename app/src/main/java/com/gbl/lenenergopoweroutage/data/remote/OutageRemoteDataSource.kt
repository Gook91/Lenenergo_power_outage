package com.gbl.lenenergopoweroutage.data.remote

import com.gbl.lenenergopoweroutage.data.remote.connection.SiteConnection
import com.gbl.lenenergopoweroutage.data.remote.mapper.JsoupDocumentMapper
import com.gbl.lenenergopoweroutage.domain.model.Outage

class OutageRemoteDataSource(
    private val siteConnection: SiteConnection,
    private val jsoupDocumentMapper: JsoupDocumentMapper,
) {
    fun getOutages(addressList: List<String> = emptyList(), startDate: Long? = null): List<Outage> =
        if (addressList.isEmpty()) {
            getOutageList(null, startDate)
        } else {
            addressList.map { address ->
                getOutageList(address, startDate)
            }.flatten().distinct()
        }

    private fun getOutageList(address: String?, startDate: Long?): List<Outage> {
        val query = QueryBuilder(address = address, startDate = startDate).build()
        val document = siteConnection.getJsoupDocument(query)
        return jsoupDocumentMapper.jsoupDocumentToOutageList(document)
    }
}