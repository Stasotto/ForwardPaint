package com.example.data.storage

import com.example.data.models.MessageEntity
import com.example.data.models.OrderEntity

import com.example.data.storage.firebase.Firebase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DatabaseStorageImpl(
    private val firebase: Firebase,
) : DatabaseStorage {

    override val orders: Flow<List<OrderEntity>> = flow {
        while (true) {
            val orders = firebase.orders
            emit(orders)
            delay(1000)
        }
    }

    override val messages: Flow<List<MessageEntity>> = flow {
        while (true) {
            val messages = firebase.messages
            emit(messages)
            delay(1000)
        }
    }

    override suspend fun getMessages(): List<MessageEntity> {
        return firebase.messages
    }

    override suspend fun saveMessage(message: MessageEntity) {
        firebase.saveMessage(message)
    }

    override suspend fun saveOrder(order: OrderEntity, imageByte: ByteArray) {
        firebase.uploadImage(order, imageByte)
    }
}