package com.gbl.lenenergopoweroutage.domain.repository

interface OutageRepository {
    fun getOutages(addresses: List<String>, date: Long?)
}