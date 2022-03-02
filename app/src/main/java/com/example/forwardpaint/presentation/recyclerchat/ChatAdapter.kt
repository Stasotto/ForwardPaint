package com.example.forwardpaint.presentation.recyclerchat


import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.forwardpaint.presentation.models.Message

class ChatAdapter : RecyclerView.Adapter<ChatHolder>() {

    private var messageList = mutableListOf<Message>()

    override fun onBindViewHolder(holder: ChatHolder, position: Int) {
        holder.bind(messageList[position])
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatHolder {
        return ChatHolder.from(parent)
    }


    @SuppressLint("NotifyDataSetChanged")
    fun addMessages(mes: List<Message>) {
        messageList = mes.toMutableList()
        notifyDataSetChanged()
    }
}