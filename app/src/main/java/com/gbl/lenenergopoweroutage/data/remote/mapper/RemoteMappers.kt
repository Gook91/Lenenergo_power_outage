package com.gbl.lenenergopoweroutage.data.remote.mapper

import com.gbl.lenenergopoweroutage.domain.model.Outage
import org.jsoup.nodes.Document
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

object RemoteMappers : JsoupDocumentMapper {
    override fun jsoupDocumentToOutageList(document: Document): List<Outage> {
        val rowList = document.getElementsByClass(OUTAGE_ROW_CLASS)
        val outageList: List<Outage> = rowList.map { row ->

            val iterator = row.getElementsByTag(ROW_HTML_TAG).iterator()

            val region = iterator.next().text()
            val district = iterator.next().text()
            val addresses = iterator.next().text()
            val startDate = iterator.next().text()
            val startTime = iterator.next().text()
            val endDate = iterator.next().text()
            val endTime = iterator.next().text()
            iterator.next() // filial
            val res = iterator.next().text()
            val commentary = iterator.next().text()

            Outage(
                region = region,
                district = district,
                addresses = addresses,
                startDate = dateTimeStringToLong(startDate, startTime),
                endDate = dateTimeStringToLong(endDate, endTime),
                res = res,
                commentary = commentary
            )
        }
        return outageList
    }

    private fun dateTimeStringToLong(date: String, time: String): Long {

        val formatter = DateTimeFormatter.ofPattern(DATE_TIME_IMPORT_PATTERN)
        val dateAndTimeString = "$date $time"

        val formattedDate = LocalDateTime.parse(dateAndTimeString, formatter)
        return formattedDate.toEpochSecond(OffsetDateTime.now().offset)
    }

    fun dateTimeLongToString(date: Long): String {

        val formatter = DateTimeFormatter.ofPattern(DATE_EXPORT_PATTERN)
        val localDate = LocalDateTime.ofEpochSecond(date, 0, OffsetDateTime.now().offset)

        val formattedDate = formatter.format(localDate)

        return formattedDate
    }

    // Document structure
    private const val OUTAGE_ROW_CLASS = "even"
    private const val ROW_HTML_TAG = "td"

    // Date and time format
    private const val DATE_TIME_IMPORT_PATTERN = "dd-MM-yyyy HH:mm"
    private const val DATE_EXPORT_PATTERN = "dd.MM.yyyy"
}