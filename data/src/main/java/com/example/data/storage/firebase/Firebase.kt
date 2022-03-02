package com.example.data.storage.firebase

import com.example.data.models.MessageEntity
import com.example.data.models.OrderEntity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class Firebase {


    private val database by lazy { Firebase.database }
    private val messageRef by lazy { database.getReference("message") }
    private val orderRef by lazy { database.getReference("orders") }
    private val storage by lazy { Firebase.storage }
    private val imagesRef by lazy { storage.getReference("images") }

    private var _messages = mutableListOf<MessageEntity>()
    val messages: List<MessageEntity> get() = _messages

    private var _orders = mutableListOf<OrderEntity>()
    val orders: List<OrderEntity> get() = _orders


    fun saveMessage(message: MessageEntity) {
        messageRef.child(messageRef.push().key ?: "default").setValue(message)
    }

    private fun saveOrder(order: OrderEntity) {
        orderRef.child(order.numberOfOrder.toString()).setValue(order)
    }

    init {
        checkMessages()
        checkOrders()
    }

    fun uploadImage(order: OrderEntity, imageByte: ByteArray) {
        val childRef = imagesRef.child(System.currentTimeMillis().toString() + "image")
        val task = childRef.putBytes(imageByte)
        val url = task.continueWithTask {
            childRef.downloadUrl
        }
        url.addOnCompleteListener {
            order.image = it.result?.toString()
            saveOrder(order)

        }

    }

    private fun checkMessages() {
        messageRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<MessageEntity>()
                for (s in snapshot.children) {
                    val message = s.getValue(MessageEntity::class.java)
                    if (message != null) {
                        list.add(message)
                    }
                }
                _messages = list
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

    private fun checkOrders() {
        orderRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val ordersList = mutableListOf<OrderEntity>()

                for (i in snapshot.children) {
                    val order = i.getValue(OrderEntity::class.java)
                    if (order != null) {
                        ordersList.add(order)
                    }
                }
                _orders = ordersList
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}