package com.example.forwardpaint.domain

import com.example.forwardpaint.domain.models.UserDomain
import com.example.forwardpaint.domain.repository.UserRepository

class SignInUseCase(private val userrepository: UserRepository) {

    suspend fun execute(user: UserDomain): Boolean {
        return userrepository.signIn(user)
    }

}