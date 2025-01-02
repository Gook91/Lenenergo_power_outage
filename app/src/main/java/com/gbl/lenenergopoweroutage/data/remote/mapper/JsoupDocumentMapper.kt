package com.gbl.lenenergopoweroutage.data.remote.mapper

import com.gbl.lenenergopoweroutage.domain.model.Outage
import org.jsoup.nodes.Document

interface JsoupDocumentMapper {
    fun jsoupDocumentToOutageList(document: Document): List<Outage>
}