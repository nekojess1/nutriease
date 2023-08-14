package org.nekojess.nutriease.domain.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RecipeDto (
    @SerializedName("nome") val nome: String,
    @SerializedName("ingredientes") val ingredientesList: List<Ingredient>,
    @SerializedName("modo_de_preparo") val modo_de_preparo: String,
    @SerializedName("dificuldade") val dificuldade:String,
    @SerializedName("caloria") val caloria: String
): Parcelable{
    @Parcelize
    data class Ingredient(
        @SerializedName("ingrediente") val ingrediente: String,
        @SerializedName("medida") val medida: String
    ):Parcelable
}