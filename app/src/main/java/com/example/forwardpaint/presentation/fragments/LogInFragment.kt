package com.example.forwardpaint.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.forwardpaint.presentation.activities.MainActivity
import com.example.forwardpaint.R
import com.example.forwardpaint.databinding.FragmentLogInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LogInFragment : Fragment(R.layout.fragment_log_in) {

    companion object {
        const val TAG = "LogIn"

        fun newInstance() = LogInFragment()
    }

    private val binding by viewBinding(FragmentLogInBinding::bind)
    private val auth: FirebaseAuth by lazy { Firebase.auth }
    private val authListener: FirebaseAuth.AuthStateListener =
        FirebaseAuth.AuthStateListener {
            val user: FirebaseUser? = it.currentUser
            Log.d("MyLog", user?.displayName ?: "tttt")
            if (user != null) {
                openMainActivity()
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListeners()
    }

    override fun onStart() {
        super.onStart()
        auth.addAuthStateListener(authListener)
    }

    override fun onStop() {
        super.onStop()
        auth.removeAuthStateListener(authListener)
    }


    private fun setOnClickListeners() = with(binding) {
        registration.setOnClickListener {
            openRegistrationFrag()
        }
        btnLogIn.setOnClickListener {
            if (checkData()) {
                signIn(
                    emailText.text.toString().trim(),
                    passwordText.text.toString().trim()
                )
            }

        }
    }

    private fun openMainActivity() {
        startActivity(Intent(requireContext(), MainActivity::class.java))
    }

    private fun checkData(): Boolean = with(binding) {
        return if (emailText.text.isNullOrEmpty()) {
            email.error = "Введите Email"
            false
        } else if (passwordText.text.isNullOrEmpty()) {
            passwordText.error = "Введите пароль"
            false
        } else {
            true
        }
    }

    private fun openRegistrationFrag() {
        parentFragmentManager.beginTransaction()
            .addToBackStack(RegistrationFragment.TAG)
            .replace(
                R.id.fragmentContainer,
                RegistrationFragment.newInstance(),
                RegistrationFragment.TAG
            )
            .commit()
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { result ->
            if (result.isSuccessful) {
                openMainActivity()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Неправильный Email или Пароль",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

    }
}