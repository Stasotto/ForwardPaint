package com.example.forwardpaint.presentation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Order(
    val name: String?,
    val lastName: String?,
    val phoneNumber: Long?,
    val numberOfOrder: Int?,
    val typeOfOrder: String?,
    val price: Int?,
    val image: String?,
    val status: String?
) : Parcelable