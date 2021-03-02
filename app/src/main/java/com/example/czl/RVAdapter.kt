package com.example.czl

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RVAdapter : RecyclerView.Adapter<RVAdapter.InnerHolder> {

    val list: List<GoodsModel>?

    constructor(list: List<GoodsModel>?) {
        this.list = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerHolder {
        return InnerHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rv_item_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: InnerHolder, position: Int) {
        holder.rv_item_layout_title.text = list?.get(position)?.name ?: ""
        holder.rv_item_layout_subtitle.text = list?.get(position)?.price.toString() ?: ""
    }

    inner class InnerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rv_item_layout_title = itemView.findViewById<TextView>(R.id.rv_item_layout_title)
        val rv_item_layout_subtitle = itemView.findViewById<TextView>(R.id.rv_item_layout_subtitle)
    }
}