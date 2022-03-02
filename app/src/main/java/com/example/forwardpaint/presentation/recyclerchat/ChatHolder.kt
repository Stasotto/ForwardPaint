package com.example.forwardpaint.presentation.recyclerchat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.forwardpaint.R
import com.example.forwardpaint.databinding.ItemMessageBinding
import com.example.forwardpaint.presentation.models.Message

class ChatHolder(item: View) : RecyclerView.ViewHolder(item) {

    companion object {
        fun from(parent: ViewGroup) = ChatHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_message, parent, false)
        )
    }

    private val binding by lazy { ItemMessageBinding.bind(item) }

    fun bind(mes: Message) = with(binding) {
        userName.text = mes.name
        message.text = mes.message
    }
}