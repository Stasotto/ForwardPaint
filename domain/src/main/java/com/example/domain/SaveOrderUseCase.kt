package com.example.domain

import com.example.domain.models.OrderDomain
import com.example.domain.repository.UserRepository

class SaveOrderUseCase(private val repository: UserRepository) {

    suspend fun execute(order: OrderDomain, imageBytes: ByteArray) {
        repository.saveOrder(order, imageBytes)
    }
}