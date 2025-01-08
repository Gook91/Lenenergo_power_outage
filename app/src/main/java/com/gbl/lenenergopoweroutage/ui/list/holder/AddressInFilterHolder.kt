package com.gbl.lenenergopoweroutage.ui.list.holder

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.gbl.lenenergopoweroutage.databinding.ItemAddressInFilterBinding

class AddressInFilterHolder(
    private val binding: ItemAddressInFilterBinding
) : ViewHolder(binding.root) {

    fun onBind(
        address: String,
        onDeleteClick: () -> Unit,
    ) = with(binding) {
        addressText.text = address
        deleteAddress.setOnClickListener { onDeleteClick() }
    }
}