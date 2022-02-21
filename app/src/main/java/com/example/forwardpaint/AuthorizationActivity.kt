package com.example.forwardpaint

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.forwardpaint.presentation.fragments.LogInFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AuthorizationActivity : AppCompatActivity(R.layout.activity_authorization) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = Firebase.database
        val myRef = database.getReference("message")

        myRef.setValue("Hello, World!")
        openLogInFrag()
    }

    private fun openLogInFrag() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, LogInFragment.newInstance(), LogInFragment.TAG)
            .commit()
    }

}