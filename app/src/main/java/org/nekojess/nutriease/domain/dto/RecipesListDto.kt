package org.nekojess.nutriease.domain.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RecipesListDto (
    @SerializedName("receitas") val receitas: List<RecipeDto>
): Parcelable