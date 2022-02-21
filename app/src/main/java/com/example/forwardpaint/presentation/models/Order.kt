package com.example.forwardpaint.presentation.models

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Order(
    val name: String,
    val lastName: String,
    val phoneNumber: Long,
    val numberOfOrder: Int,
    val typeOfOrder: String,
    val price: Int,
    val image: Bitmap?,
    val status: String
): Parcelable {
}