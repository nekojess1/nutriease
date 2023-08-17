package org.nekojess.nutriease.domain.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RecipeDto (
    @SerializedName("name_recipe") val nameRecipe: String,
    @SerializedName("ingredients_list") val ingredientsList: List<Ingredient>,
    @SerializedName("method") val method: String,
    @SerializedName("difficulty") val difficulty:String,
    @SerializedName("calorie") val calorie: String,
    @SerializedName("protein") val protein: String,
    @SerializedName("carbohydrate") val carbohydrate: String
): Parcelable{
    @Parcelize
    data class Ingredient(
        @SerializedName("ingredient") val ingredient: String,
        @SerializedName("measure") val measure: String
    ):Parcelable
}