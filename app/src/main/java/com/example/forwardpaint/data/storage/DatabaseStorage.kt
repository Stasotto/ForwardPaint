package com.example.forwardpaint.data.storage

import com.example.forwardpaint.data.models.UserEntity

interface DatabaseStorage {

    suspend fun signIn(user: UserEntity): Boolean

    suspend fun signUp(user: UserEntity): Boolean
}