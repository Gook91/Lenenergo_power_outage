package com.gbl.lenenergopoweroutage.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.gbl.lenenergopoweroutage.databinding.HeaderAddAddressInFilterBinding
import com.gbl.lenenergopoweroutage.ui.list.holder.HeaderAddressInFilterHolder

class HeaderAddressInFilterAdapter(
    private val onClickAdd: (String) -> Unit
) : Adapter<HeaderAddressInFilterHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderAddressInFilterHolder =
        HeaderAddressInFilterHolder(
            HeaderAddAddressInFilterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: HeaderAddressInFilterHolder, position: Int) {
        holder.onBind(onClickAdd)
    }

    override fun getItemCount(): Int = 1
}