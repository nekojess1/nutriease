package org.nekojess.nutriease.domain.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginDto (
    val email: String,
    val password: String
): Parcelable

