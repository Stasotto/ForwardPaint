package com.example.forwardpaint.domain.repository

import com.example.forwardpaint.domain.models.UserDomain

interface UserRepository {

    suspend fun signIn(user: UserDomain): Boolean

    suspend fun signUp(user: UserDomain): Boolean
}