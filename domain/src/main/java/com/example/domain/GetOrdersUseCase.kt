package com.example.domain


import com.example.domain.models.OrderDomain
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow



class GetOrdersUseCase(private val repository: UserRepository) {
    val orders: Flow<List<OrderDomain>> = repository.orders

}