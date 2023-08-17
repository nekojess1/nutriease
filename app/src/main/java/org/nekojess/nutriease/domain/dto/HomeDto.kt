package org.nekojess.nutriease.domain.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HomeDto (
    var user: UserDto?,
    var patients: List<PatientDto>
): Parcelable

