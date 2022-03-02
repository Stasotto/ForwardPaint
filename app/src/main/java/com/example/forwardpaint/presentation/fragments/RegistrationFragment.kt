package com.example.forwardpaint.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.forwardpaint.R
import com.example.forwardpaint.databinding.FragmentRegistrationBinding
import com.example.forwardpaint.presentation.activities.MainActivity
import com.example.forwardpaint.presentation.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegistrationFragment : Fragment(R.layout.fragment_registration) {


    companion object {
        private const val EMPLOYEE_CODE = "375336510107"
        const val TAG = "Registration"

        fun newInstance() = RegistrationFragment()
    }

    private val auth: FirebaseAuth by lazy { Firebase.auth }

    private val binding by viewBinding<FragmentRegistrationBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTypeOfRegistration()
        binding.btnRegistr.setOnClickListener {
            signUpUser()
        }
    }


    private fun checkDataIsEmpty(): Boolean {
        with(binding) {
            val listOfView = listOf(
                firstNameText,
                lastNameText,
                emailText,
                passwordText,
                repPasswordText,
                employeeCodeText
            )
            employeeCodeText.isVisible = false
            if (employeeCodeText.isVisible) {
            }
            var isEmpty = false
            listOfView.forEachIndexed { index, textInputEditText ->
                if (textInputEditText.isVisible) {
                    if (textInputEditText.text.isNullOrEmpty()) {
                        isEmpty = true
                        textInputEditText.error = when (index) {
                            0 -> "Введите имя"
                            1 -> "Введите фамилию"
                            2 -> "Введите Email"
                            3 -> "Введите пароль"
                            4 -> "Повтарите пароль"
                            else -> "Введите код"
                        }
                    }
                }
            }
            return isEmpty
        }
    }

    private fun checkIfPasswordsMath(): Boolean {
        with(binding) {
            return passwordText.text.toString() == repPasswordText.text.toString()
        }
    }

    private fun signUpUser() = with(binding) {
        if (!checkDataIsEmpty() && checkIfPasswordsMath()) {
            if (employeeCodeText.isVisible) {
                if (employeeCodeText.text.toString() == EMPLOYEE_CODE) {
                    signUp(
                        User(
                            userName = firstNameText.text.toString() + " " + lastNameText.text.toString(),
                            email = emailText.text.toString().trim(),
                            password = passwordText.text.toString().trim()

                        )
                    )
                } else {
                    employeeCodeText.error = "Неверный код"
                    Toast.makeText(
                        requireContext(),
                        "Такого кода не существует",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                signUp(
                    User(
                        userName = firstNameText.text.toString() + " " + lastNameText.text.toString(),
                        email = emailText.text.toString().trim(),
                        password = passwordText.text.toString().trim()
                    )
                )
            }
        } else {
            repPasswordText.error = "Неверный пароль"
            Toast.makeText(requireContext(), "Пароль не совпадает", Toast.LENGTH_SHORT).show()
        }
    }


    private fun setTypeOfRegistration() = with(binding) {
        client.setOnClickListener {
            client.setBackgroundResource(R.drawable.registration_background_secondary)
            client.setTextColor(resources.getColor(R.color.black))
            employee.setBackgroundResource(R.drawable.registartion_textvie_background)
            employee.setTextColor(resources.getColor(R.color.defColorTextView))
            employeeCode.isVisible = false
        }
        employee.setOnClickListener {
            employee.setBackgroundResource(R.drawable.registration_background_secondary)
            employee.setTextColor(resources.getColor(R.color.black))
            client.setBackgroundResource(R.drawable.registartion_textvie_background)
            client.setTextColor(resources.getColor(R.color.defColorTextView))
            employeeCode.isVisible = true
        }
    }


    private fun signUp(user: User) {
        auth.createUserWithEmailAndPassword(user.email, user.password).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(requireContext(), "Регистрация успешна", Toast.LENGTH_SHORT).show()
                openMainActivity(user)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Этот Email уже зарегистрирован",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun openMainActivity(user: User) {
        val intent = Intent(requireContext(), MainActivity::class.java)
        intent.putExtra("name", user.userName)
        startActivity(intent)
    }
}