package com.example.forwardpaint.data.storage.firebase

import com.example.forwardpaint.data.models.UserEntity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Firebase {

    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val authListener: FirebaseAuth.AuthStateListener =
        FirebaseAuth.AuthStateListener {
            val user: FirebaseUser? = auth.currentUser
            if (user != null) {

            }
        }

    suspend fun signIn(userEntity: UserEntity): Boolean { 
        var result: Boolean
        auth.signInWithEmailAndPassword(userEntity.email, userEntity.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                   result = true
                } else {
                   result = false
                }
            }
        return result
    }


}