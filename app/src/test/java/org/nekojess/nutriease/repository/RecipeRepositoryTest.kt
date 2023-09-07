package org.nekojess.nutriease.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.nekojess.nutriease.api.RecipeService
import org.nekojess.nutriease.data.repository.recipe.RecipeRepositoryImpl
import org.nekojess.nutriease.domain.dto.RecipeDto
import org.nekojess.nutriease.domain.dto.RecipesListDto

@ExperimentalCoroutinesApi
class RecipeRepositoryTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val recipeService = mock<RecipeService>()

    private val repository = RecipeRepositoryImpl(recipeService)

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope = TestCoroutineScope(testCoroutineDispatcher)

    @Before
    fun setup() {
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
        testCoroutineScope.cleanupTestCoroutines()
    }

    @Test
    fun whenGetRecipes_shouldReturnListOfRecipes() = testCoroutineDispatcher.runBlockingTest {
        val mockedRecipeList = mockedList()

        whenever(recipeService.getRecipes(mapOf("input_text" to "ovo,leite,cenoura"))).thenReturn(mockedList())
        val result = repository.getRecipeData(mapOf("input_text" to "ovo,leite,cenoura"))

        assertEquals(mockedRecipeList, result)
    }

    private fun mockedList(): RecipesListDto {
        return RecipesListDto(
            recipes = listOf(
                RecipeDto(
                    nameRecipe = "Omelete de cenoura",
                    ingredientsList = listOf(
                        RecipeDto.Ingredient(ingredient = "ovo", measure = "2 unidades"),
                        RecipeDto.Ingredient(ingredient = "cenoura", measure = "1 unidade")
                    ),
                    method = "Rale a cenoura e misture com os ovos batidos. \nTempere a gosto e despeje em uma frigideira antiaderente. \nCozinhe em fogo baixo até firmar dos dois lados. \nSirva quente.",
                    difficulty = "Dificuldade: Fácil",
                    calorie = "150 Kcal",
                    protein = "450g",
                    carbohydrate = "49g"
                )
            )
        )
    }
}