package org.nekojess.nutriease.domain.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import org.nekojess.nutriease.util.StringUtils.EMPTY_STRING

@Parcelize
data class OnboardingDto (
    val title: String = EMPTY_STRING,
    val description: String = EMPTY_STRING,
    val imageId: Int?,
    val onClickListener: @RawValue (() -> Unit)? = null
): Parcelable

