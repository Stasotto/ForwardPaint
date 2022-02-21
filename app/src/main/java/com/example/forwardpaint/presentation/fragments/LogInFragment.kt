package com.example.forwardpaint.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.forwardpaint.MainActivity
import com.example.forwardpaint.R
import com.example.forwardpaint.databinding.FragmentLogInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LogInFragment : Fragment(R.layout.fragment_log_in) {

    companion object {
        const val TAG = "LogIn"

        fun newInstance() = LogInFragment()
    }

    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val authListener: FirebaseAuth.AuthStateListener =
        FirebaseAuth.AuthStateListener {
            val user: FirebaseUser? = auth.currentUser
            if (user != null) {
                openMainActivity()
            }
        }

    private val binding by viewBinding<FragmentLogInBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListeners()
        authListener
    }

    private fun setOnClickListeners() = with(binding) {
        registration.setOnClickListener {
            openRegistrationFrag()
        }
        btnLogIn.setOnClickListener {

            signIn(emailText.text.toString(), passwordText.text.toString())
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
        if (checkData()) {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    openMainActivity()
                    Toast.makeText(requireContext(), "Авторизация успешна", Toast.LENGTH_SHORT)
                        .show()

                } else {
                    Toast.makeText(
                        requireContext(),
                        "Неверный Email или пароль",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }
    }
}