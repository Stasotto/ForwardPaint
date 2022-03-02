package com.example.forwardpaint.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.forwardpaint.R
import com.example.forwardpaint.databinding.FragmentChatBinding
import com.example.forwardpaint.presentation.activities.MainActivity
import com.example.forwardpaint.presentation.models.Message
import com.example.forwardpaint.presentation.recyclerchat.ChatAdapter
import com.example.forwardpaint.presentation.viewmodels.ChatFragViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChatFragment : Fragment(R.layout.fragment_chat) {

    companion object {
        const val TAG = "Chat"

        fun newInstance() = ChatFragment()
    }

    private val binding by viewBinding(FragmentChatBinding::bind)
    private val chatAdapter: ChatAdapter by lazy { ChatAdapter() }
    private val viewModel by viewModel<ChatFragViewModel>()
    private val auth by lazy { Firebase.auth }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).setSupportActionBar(binding.toolbar)
        setSendMessageListener()
        setUpChatRecycler()
    }

    private fun setUpChatRecycler() = with(binding) {
        chatRecycler.adapter = chatAdapter
        chatRecycler.layoutManager = LinearLayoutManager(requireContext())

        viewModel.messages.observe(viewLifecycleOwner, { messages ->
            chatAdapter.addMessages(messages)
        })
    }

    private fun setSendMessageListener() {

        binding.send.setOnClickListener {
            val mes = binding.Edittext.text.toString()
            if (mes.isNotEmpty()) {
                viewModel.saveMessage(
                    Message(
                        name = auth.currentUser?.displayName,
                        message = mes
                    )
                )
            }

            val i: InputMethodManager =
                requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            i.hideSoftInputFromWindow(
                binding.Edittext.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
            binding.Edittext.text.clear()
        }
    }
}