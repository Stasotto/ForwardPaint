package com.example.forwardpaint.domain

import com.example.forwardpaint.domain.models.UserDomain
import com.example.forwardpaint.domain.repository.UserRepository

class SignUpUseCase(private val userRepository: UserRepository) {

    suspend fun execute(user: UserDomain): Boolean {
        return userRepository.signUp(user)
    }
}