package com.example.forwardpaint.data

import com.example.forwardpaint.domain.models.UserDomain
import com.example.forwardpaint.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepositoryImpl() : UserRepository

    override suspend fun signIn(user: UserDomain): Boolean {
        return withContext(Dispatchers.IO) {

    }

    override suspend fun signUp(user: UserDomain): Boolean {
        TODO("Not yet implemented")
    }
}