package org.nekojess.nutriease.domain.dto

import android.os.Parcelable
import com.google.firebase.firestore.PropertyName
import kotlinx.android.parcel.Parcelize
import org.nekojess.nutriease.util.StringUtils.EMPTY_STRING

@Parcelize
data class UserDto (
    @get:PropertyName("name")
    @set:PropertyName("name")
    var name: String = EMPTY_STRING,

    @get:PropertyName("birthday")
    @set:PropertyName("birthday")
    var birthday: String = EMPTY_STRING,

    @get:PropertyName("crn")
    @set:PropertyName("crn")
    var crn: String = EMPTY_STRING,

    @get:PropertyName("uf")
    @set:PropertyName("uf")
    var uf: String = EMPTY_STRING,

    @get:PropertyName("city")
    @set:PropertyName("city")
    var city: String = EMPTY_STRING,

    @get:PropertyName("email")
    @set:PropertyName("email")
    var email: String = EMPTY_STRING,

    @get:PropertyName("phone")
    @set:PropertyName("phone")
    var phone: String = EMPTY_STRING
): Parcelable

