package com.gbl.lenenergopoweroutage.ui.list.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.gbl.lenenergopoweroutage.R
import com.gbl.lenenergopoweroutage.databinding.ItemOutageBinding
import com.gbl.lenenergopoweroutage.domain.model.Outage
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class OutageHolder(
    private val binding: ItemOutageBinding
) : ViewHolder(binding.root) {

    fun onBind(outage: Outage) = with(binding) {

        val fullAddresses = outage.addresses
        val collapseAddresses = fullAddresses.replace(
            "\n",
            itemView.context.getString(R.string.address_separator)
        )
        dates.text = getStringDate(outage.startDate, outage.endDate)
        addresses.text = collapseAddresses
        commentary.text = outage.commentary

        expandButton.setOnCheckedChangeListener { _, isChecked ->

            if (isChecked) {
                addresses.text = fullAddresses
                addresses.maxLines = Int.MAX_VALUE
                commentary.visibility = View.VISIBLE
                expandButton.contentDescription = itemView.context.getString(R.string.expand_less)

            } else {
                addresses.text = collapseAddresses
                addresses.maxLines = ADDRESSES_MAX_LINES_WHEN_COLLAPSED
                commentary.visibility = View.GONE
                expandButton.contentDescription = itemView.context.getString(R.string.expand_more)
            }
        }
        expandButton.isChecked = false
    }

    private fun getStringDate(startDate: Long, endDate: Long): String {

        val startDateLocal = LocalDateTime.ofEpochSecond(startDate, 0, OffsetDateTime.now().offset)
        val endDateLocal = LocalDateTime.ofEpochSecond(endDate, 0, OffsetDateTime.now().offset)
        val isDateSame = startDateLocal.toLocalDate().isEqual(endDateLocal.toLocalDate())

        val startDateString = startDateLocal.format(
            DateTimeFormatter.ofPattern(if (isDateSame) TIME_PATTERN else DATE_TIME_PATTERN)
        )
        val endDateString = endDateLocal.format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN))

        return itemView.context.getString(
            R.string.date_outage_template,
            startDateString,
            endDateString
        )
    }

    private companion object {
        private const val ADDRESSES_MAX_LINES_WHEN_COLLAPSED = 2
        private const val TIME_PATTERN = "HH:mm"
        private const val DATE_TIME_PATTERN = "HH:mm dd.MM.yyyy"
    }
}