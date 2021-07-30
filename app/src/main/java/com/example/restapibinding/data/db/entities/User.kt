package com.example.restapibinding.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

const val CURRENT_USER_ID = 0

@Entity
data class User(
    var id: Int? = null,
    var mobileNumber: Long,
    var name: String? = null,
    var gender: String? = null,
    var dob: String? = null,
    var address: String?= null,
    var pincode: Int
){
    @PrimaryKey(autoGenerate = true)
    var uid: Int = CURRENT_USER_ID
}