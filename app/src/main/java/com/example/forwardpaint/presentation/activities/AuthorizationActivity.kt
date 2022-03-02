package com.example.forwardpaint.presentation.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.forwardpaint.R
import com.example.forwardpaint.presentation.fragments.LogInFragment

class AuthorizationActivity : AppCompatActivity(R.layout.activity_authorization) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openLogInFrag()
    }

    private fun openLogInFrag() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, LogInFragment.newInstance(), LogInFragment.TAG)
            .commit()
    }
}