package com.gbl.lenenergopoweroutage.domain.model

data class Outage(
    val region: String,
    val district: String,
    val addresses: String,
    val startDate: Long,
    val endDate: Long,
    val res: String,
    val commentary: String,
)
