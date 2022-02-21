package com.example.forwardpaint.presentation.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.forwardpaint.R
import com.example.forwardpaint.databinding.FragmentRegistrationBinding
import com.google.firebase.auth.FirebaseAuth

class RegistrationFragment : Fragment(R.layout.fragment_registration) {


    companion object {
        const val TAG = "Registration"

        fun newInstance() = RegistrationFragment()
    }

    private val binding by viewBinding<FragmentRegistrationBinding>()
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTypeOfRegistration()


    }

    private fun setTypeOfRegistration() = with(binding){
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

    private fun registration(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task->
            if(task.isSuccessful) {
                Toast.makeText(requireContext(), "Регистрация успешна", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Авторизация провалена", Toast.LENGTH_SHORT).show()
            }
        }

    }
}