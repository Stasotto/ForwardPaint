package com.example.data.models

class OrderEntity {
    var name: String? = null
    var lastName: String? = null
    var phoneNumber: Long? = null
    var numberOfOrder: Int? = null
    var typeOfOrder: String? = null
    var price: Int? = null
    var image: String? = null
    var status: String? = null

    constructor()

    constructor( name: String?,
                 lastName: String?,
                 phoneNumber: Long?,
                 numberOfOrder: Int?,
                 typeOfOrder: String?,
                 price: Int?,
                 image: String?,
                 status: String?) {
        this.name = name
        this.lastName = lastName
        this.phoneNumber = phoneNumber
        this.numberOfOrder = numberOfOrder
        this.typeOfOrder = typeOfOrder
        this.price= price
        this.image = image
        this.status = status
    }
}


