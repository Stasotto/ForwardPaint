package com.example.forwardpaint.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.GetOrdersUseCase
import com.example.domain.SaveOrderUseCase
import com.example.forwardpaint.presentation.models.Order
import com.example.forwardpaint.presentation.toOrder
import com.example.forwardpaint.presentation.toOrderDomain
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainFragViewModel(
    private val getOrdersUseCase: GetOrdersUseCase,
    private val saveOrderUseCase: SaveOrderUseCase
) : ViewModel() {

    private val _orderForRecycler = MutableLiveData<List<Order>>()
    val orderForRecycler: LiveData<List<Order>> get() = _orderForRecycler


    init {
        loadOrders()
    }

    private fun loadOrders() {
        viewModelScope.launch {
            getOrdersUseCase.orders.collect { ordersList ->
                _orderForRecycler.value = ordersList.map { orderDomain ->
                    orderDomain.toOrder()
                }
            }
        }
    }

    fun saveOrder(order: Order, imageBytes: ByteArray) {
        viewModelScope.launch {
            saveOrderUseCase.execute(order.toOrderDomain(), imageBytes)
        }
    }
}