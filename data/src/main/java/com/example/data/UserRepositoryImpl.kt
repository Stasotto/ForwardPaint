package com.example.data

import com.example.data.storage.DatabaseStorage
import com.example.domain.models.MessageDomain
import com.example.domain.models.OrderDomain
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    private val databaseStorage: DatabaseStorage,
) : UserRepository {

    override val message: Flow<List<MessageDomain>> = databaseStorage.messages.map { messageList ->
        messageList.map { messageEntity ->
            messageEntity.toMessageDomain()
        }
    }.flowOn(Dispatchers.IO)
    override val orders: Flow<List<OrderDomain>> = databaseStorage.orders.map { orderList ->
        orderList.map { orderEntity ->
            orderEntity.toOrderDomain()
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getMessages(): List<MessageDomain> {
        return withContext(Dispatchers.IO) {
            databaseStorage.getMessages().map { messageEntity ->
                messageEntity.toMessageDomain()
            }
        }
    }

    override suspend fun saveMessage(message: MessageDomain) {
        withContext(Dispatchers.IO) {
            databaseStorage.saveMessage(message.toMessageEntity())
        }
    }

    override suspend fun saveOrder(order: OrderDomain, imageBytes: ByteArray) {
        withContext(Dispatchers.IO) {
            databaseStorage.saveOrder(order.toOrderEntity(), imageBytes)
        }
    }
}