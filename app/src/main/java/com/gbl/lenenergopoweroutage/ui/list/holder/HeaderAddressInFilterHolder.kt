package com.gbl.lenenergopoweroutage.ui.list.holder

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.gbl.lenenergopoweroutage.databinding.HeaderAddAddressInFilterBinding

class HeaderAddressInFilterHolder(
    private val binding: HeaderAddAddressInFilterBinding
) : ViewHolder(binding.root) {

    fun onBind(onClickAdd: (String) -> Unit) = with(binding) {
        addButton.setOnClickListener {
            onClickAdd(addAddressEditText.text.trim().toString())
        }
    }
}