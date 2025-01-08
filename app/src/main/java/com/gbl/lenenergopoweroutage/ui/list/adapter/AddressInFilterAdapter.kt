package com.gbl.lenenergopoweroutage.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.gbl.lenenergopoweroutage.databinding.ItemAddressInFilterBinding
import com.gbl.lenenergopoweroutage.ui.list.diffUtil.AddressInFilterDiffUtil
import com.gbl.lenenergopoweroutage.ui.list.holder.AddressInFilterHolder

class AddressInFilterAdapter(
    private val onDeleteItem: (String) -> Unit,
) : ListAdapter<String, AddressInFilterHolder>(AddressInFilterDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressInFilterHolder =
        AddressInFilterHolder(
            ItemAddressInFilterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: AddressInFilterHolder, position: Int) {
        val address = getItem(position)
        holder.onBind(address) { onDeleteItem(address) }
    }

}