package org.nekojess.nutriease.domain.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.nekojess.nutriease.util.StringUtils.EMPTY_STRING

@Parcelize
data class SignUpDto (
    val name: String,
    val birthday: String,
    val crn: String,
    val uf: String,
    val city: String,
    val email: String = EMPTY_STRING,
    val phone: String = EMPTY_STRING
): Parcelable

