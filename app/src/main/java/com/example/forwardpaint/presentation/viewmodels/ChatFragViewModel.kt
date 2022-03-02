package com.example.forwardpaint.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.GetMessage
import com.example.domain.SaveMessageUseCase
import com.example.forwardpaint.presentation.models.Message
import com.example.forwardpaint.presentation.toMessage
import com.example.forwardpaint.presentation.toMessageDomain
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class ChatFragViewModel(
    private val saveMessageUseCase: SaveMessageUseCase,
    private val getMessages: GetMessage
) : ViewModel() {

    private val _messages = MutableLiveData<List<Message>>()
    val messages: LiveData<List<Message>> get() = _messages

    init {
        loadMessages()
    }

    private fun loadMessages() {
        viewModelScope.launch {
            getMessages.message.collect {
                _messages.value = it.map { messageDomain ->
                messageDomain.toMessage()
                }
            }

        }
    }

    fun saveMessage(message: Message) {
        viewModelScope.launch {
            saveMessageUseCase.execute(message.toMessageDomain())
        }
    }
}