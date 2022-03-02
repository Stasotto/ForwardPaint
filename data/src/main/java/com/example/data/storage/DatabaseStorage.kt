package com.example.data.storage

import com.example.data.models.MessageEntity
import com.example.data.models.OrderEntity
import kotlinx.coroutines.flow.Flow

interface DatabaseStorage {
    val messages: Flow<List<MessageEntity>>
    val orders: Flow<List<OrderEntity>>

    suspend fun getMessages(): List<MessageEntity>

    suspend fun saveMessage(message: MessageEntity)

    suspend fun saveOrder(order: OrderEntity, imageByte: ByteArray)
}