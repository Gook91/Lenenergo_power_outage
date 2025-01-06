package com.gbl.lenenergopoweroutage.ui.list.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.gbl.lenenergopoweroutage.databinding.ItemOutageBinding
import com.gbl.lenenergopoweroutage.domain.model.Outage
import com.gbl.lenenergopoweroutage.ui.list.holder.OutageHolder

class OutageAdapter(
    defaultData: List<Outage> = emptyList()
) : Adapter<OutageHolder>() {

    var data: List<Outage> = defaultData
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OutageHolder =
        OutageHolder(ItemOutageBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: OutageHolder, position: Int) =
        holder.onBind(data[position])

    override fun getItemCount(): Int = data.size
}