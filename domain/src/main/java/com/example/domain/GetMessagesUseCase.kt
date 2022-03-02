package com.example.domain

import com.example.domain.models.MessageDomain
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

interface GetMessage {
    val message: Flow<List<MessageDomain>>
}

class GetMessagesUseCase(private val repository: UserRepository) : GetMessage {
    override val message: Flow<List<MessageDomain>> = repository.message.flowOn(Dispatchers.IO)

    suspend fun execute(): List<MessageDomain> {
        return repository.getMessages()
    }
}