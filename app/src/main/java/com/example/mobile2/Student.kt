package com.example.mobile2

data class Student (
    val id: String,
    var name: String,
    var phone: String,
    var address: String,
    var isChecked: Boolean = false,
    var profileImageId: Int = R.drawable.default_profile
)
