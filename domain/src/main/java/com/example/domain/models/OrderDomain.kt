package com.example.domain.models


data class OrderDomain(
    val name: String?,
    val lastName: String?,
    val phoneNumber: Long?,
    val numberOfOrder: Int?,
    val typeOfOrder: String?,
    val price: Int?,
    val image: String?,
    val status: String?
) {
}