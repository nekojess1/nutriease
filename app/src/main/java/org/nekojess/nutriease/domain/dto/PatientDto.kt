package org.nekojess.nutriease.domain.dto

import android.os.Parcelable
import com.google.firebase.firestore.PropertyName
import kotlinx.parcelize.Parcelize
import org.nekojess.nutriease.util.StringUtils.EMPTY_STRING

@Parcelize
data class PatientDto (
    @get:PropertyName("name")
    @set:PropertyName("name")
    var name: String = EMPTY_STRING,

    @get:PropertyName("birthday")
    @set:PropertyName("birthday")
    var birthday: String = EMPTY_STRING,

    @get:PropertyName("genre")
    @set:PropertyName("genre")
    var genre: String = EMPTY_STRING,

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
    var phone: String = EMPTY_STRING,

    @get:PropertyName("weight")
    @set:PropertyName("weight")
    var weight: String = EMPTY_STRING,

    @get:PropertyName("height")
    @set:PropertyName("height")
    var height: String = EMPTY_STRING,

    @get:PropertyName("paf")
    @set:PropertyName("paf")
    var paf: String = EMPTY_STRING,

    @get:PropertyName("target")
    @set:PropertyName("target")
    var target: String = EMPTY_STRING,

    @get:PropertyName("foodRestriction")
    @set:PropertyName("foodRestriction")
    var foodRestriction: String = EMPTY_STRING,

    @get:PropertyName("foodPreference")
    @set:PropertyName("food foodPreference")
    var foodPreference: String = EMPTY_STRING
): Parcelable

