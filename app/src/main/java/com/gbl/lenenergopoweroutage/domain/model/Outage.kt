package com.gbl.lenenergopoweroutage.domain.model

data class Outage(
    val addresses: String,
    val startDate: Long,
    val endDate: Long,
    val commentary: String,
)
