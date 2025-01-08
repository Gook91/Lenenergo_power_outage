package com.gbl.lenenergopoweroutage.ui.list.diffUtil

import androidx.recyclerview.widget.DiffUtil

class AddressInFilterDiffUtil : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
        oldItem == newItem
}