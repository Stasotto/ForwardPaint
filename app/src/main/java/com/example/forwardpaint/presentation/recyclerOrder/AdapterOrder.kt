package com.example.forwardpaint.presentation.recyclerOrder

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.forwardpaint.presentation.models.Order

class AdapterOrder(
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<OrderHolder>() {

    private var dataList = mutableListOf<Order>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderHolder {
        return OrderHolder.from(parent, onItemClickListener)
    }

    override fun onBindViewHolder(holder: OrderHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addOrders(orders: List<Order>) {
        dataList = orders.toMutableList()
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addOrder(order: Order) {
        dataList.add(order)
        notifyDataSetChanged()
    }

}