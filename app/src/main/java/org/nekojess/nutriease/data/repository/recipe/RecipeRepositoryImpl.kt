package org.nekojess.nutriease.data.repository.recipe

import org.nekojess.nutriease.api.RecipeService

class RecipeRepositoryImpl(private val recipeService: RecipeService) : RecipeRepository {

    override suspend fun getRecipeData(ingredient: Map<String, String>) =
        recipeService.getRecipes(ingredient)

}