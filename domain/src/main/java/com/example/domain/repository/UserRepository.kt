package com.example.domain.repository

import com.example.domain.models.MessageDomain
import com.example.domain.models.OrderDomain
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    val message: Flow<List<MessageDomain>>
    val orders: Flow<List<OrderDomain>>

    suspend fun getMessages(): List<MessageDomain>

    suspend fun saveMessage(message: MessageDomain)

    suspend fun saveOrder(order: OrderDomain, imageBytes: ByteArray)
}