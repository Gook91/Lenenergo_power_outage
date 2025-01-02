package com.gbl.lenenergopoweroutage.data.remote

import com.gbl.lenenergopoweroutage.data.remote.mapper.RemoteMappers

data class QueryBuilder(
    var address: String? = null,
    var startDate: Long? = null,
) {
    fun build(): String {
        return buildString {
            append("$SITE?")
            if (!address.isNullOrBlank()) append("street=$address&")
            startDate?.let {
                val formattedDate = RemoteMappers.dateTimeLongToString(it)
                append("date_start=$formattedDate&")
            }
        }
    }

    private companion object {
        private const val SITE = "https://rosseti-lenenergo.ru/planned_work/"
    }
}