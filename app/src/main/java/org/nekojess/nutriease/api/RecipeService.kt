package org.nekojess.nutriease.api

import org.nekojess.nutriease.domain.dto.RecipesListDto
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RecipeService {
    @POST("/generate_text")
    suspend fun getRecipes(@Body ingredient: Map<String,String>): RecipesListDto
}