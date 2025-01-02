package com.gbl.lenenergopoweroutage.data.remote.connection

import org.jsoup.nodes.Document

interface SiteConnection {
    fun getJsoupDocument(url: String): Document
}