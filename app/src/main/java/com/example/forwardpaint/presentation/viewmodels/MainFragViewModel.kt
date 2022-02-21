package com.example.forwardpaint.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.forwardpaint.presentation.models.Order

class MainFragViewModel : ViewModel() {

    private val _orderForRecycler = MutableLiveData<List<Order>>()
    val orderForRecycler: LiveData<List<Order>> get() = _orderForRecycler

    val order2 = MutableLiveData<Order>()


    init {
        val list = listOf<Order>(
            Order(
                "Kiril",
                "Berg",
                375333339922,
                1,
                "Очистка",
                222,
                null,
                "Принят"
            ),

            Order(
                "Kiril",
                "Berg",
                375333339922,
                7,
                "Очистка",
                555,
                null,
                "Принят"
            )
        )

        _orderForRecycler.value = list

    }

    fun load(order: Order) {
        order2.value = order
    }
}