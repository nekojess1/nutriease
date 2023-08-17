package org.nekojess.nutriease.data.repository.recipe

import org.nekojess.nutriease.domain.dto.RecipesListDto

interface RecipeRepository {
    suspend fun getRecipeData(ingredient: Map<String, String>): RecipesListDto
}
