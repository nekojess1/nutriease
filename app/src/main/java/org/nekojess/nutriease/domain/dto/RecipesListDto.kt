package org.nekojess.nutriease.domain.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RecipesListDto (
    @SerializedName("recipes") val recipes: List<RecipeDto>
): Parcelable