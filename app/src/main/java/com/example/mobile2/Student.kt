package com.example.mobile2

import android.os.Parcelable
import androidx.versionedparcelable.VersionedParcelize
import kotlinx.parcelize.Parcelize

@Parcelize
data class Student (
    val id: String,
    var name: String,
    var phone: String,
    var address: String,
    var isChecked: Boolean = false,
    var profileImageId: Int = R.drawable.default_profile
): Parcelable
