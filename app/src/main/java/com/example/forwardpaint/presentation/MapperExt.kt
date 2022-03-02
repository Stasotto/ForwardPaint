package com.example.forwardpaint.presentation

import com.example.domain.models.MessageDomain
import com.example.domain.models.OrderDomain
import com.example.forwardpaint.presentation.models.Message
import com.example.forwardpaint.presentation.models.Order

fun MessageDomain.toMessage() = Message(
    name = name,
    message = message
)
fun Message.toMessageDomain() = MessageDomain(
    name = name,
    message = message
)

fun Order.toOrderDomain() = OrderDomain(
    name = name,
    lastName = lastName,
    phoneNumber = phoneNumber,
    numberOfOrder = numberOfOrder,
    typeOfOrder = typeOfOrder,
    price = price,
    image = image,
    status = status
)

fun OrderDomain.toOrder() = Order(
    name = name,
    lastName = lastName,
    phoneNumber = phoneNumber,
    numberOfOrder = numberOfOrder,
    typeOfOrder = typeOfOrder,
    price = price,
    image = image,
    status = status
)