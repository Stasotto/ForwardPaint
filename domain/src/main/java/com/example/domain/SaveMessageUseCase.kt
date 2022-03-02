package com.example.domain

import com.example.domain.models.MessageDomain
import com.example.domain.repository.UserRepository

class SaveMessageUseCase(
    private val repository: UserRepository
) {

    suspend fun execute(message: MessageDomain) {
        repository.saveMessage(message)
    }
}