package com.example.restapibinding.data.network.responses.pincode

data class PincodeDetailsItem(
    val Message: String,
    val PostOffice: List<PostOffice>,
    val Status: String
)