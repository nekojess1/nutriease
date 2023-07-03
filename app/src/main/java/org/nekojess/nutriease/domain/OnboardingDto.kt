package org.nekojess.nutriease.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.nekojess.nutriease.util.StringUtils.EMPTY_STRING

@Parcelize
data class OnboardingDto (
    val title: String = EMPTY_STRING,
    val description: String = EMPTY_STRING,
    val imageId: Int?
): Parcelable
