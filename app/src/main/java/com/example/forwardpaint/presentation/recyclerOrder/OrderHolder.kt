package com.example.forwardpaint.presentation.recyclerOrder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.forwardpaint.R
import com.example.forwardpaint.databinding.ItemOrderBinding
import com.example.forwardpaint.presentation.models.Order

class OrderHolder(
    private val onItemClickListener: OnItemClickListener,
    item: View) : RecyclerView.ViewHolder(item) {

    companion object {
        fun from(parent: ViewGroup, onItemClickListener: OnItemClickListener) = OrderHolder(
            onItemClickListener,
            LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        )
    }

    private val binding: ItemOrderBinding by lazy { ItemOrderBinding.bind(item) }


    fun bind(order: Order) = with(binding) {
        numberOfOrder.text = order.numberOfOrder.toString()
        status.text = order.status
        typeOrder.text = order.typeOfOrder
        imageOrder.setImageBitmap(order.image)
        root.setOnClickListener {
            onItemClickListener.setOnItemClickListener(order)
        }
    }
}