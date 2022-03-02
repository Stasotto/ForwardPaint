package com.example.data

import com.example.data.models.MessageEntity
import com.example.data.models.OrderEntity
import com.example.domain.models.MessageDomain
import com.example.domain.models.OrderDomain

fun OrderEntity.toOrderDomain() = OrderDomain(
    name = name,
    lastName = lastName,
    phoneNumber = phoneNumber,
    numberOfOrder = numberOfOrder,
    typeOfOrder = typeOfOrder,
    price = price,
    image = image,
    status = status
)

fun OrderDomain.toOrderEntity() = OrderEntity(
    name = name,
    lastName = lastName,
    phoneNumber = phoneNumber,
    numberOfOrder = numberOfOrder,
    typeOfOrder = typeOfOrder,
    price = price,
    image = image,
    status = status
)

fun MessageEntity.toMessageDomain() = MessageDomain(
    name = name,
    message = message
)

fun MessageDomain.toMessageEntity() = MessageEntity(
    name = name,
    message = message
)
