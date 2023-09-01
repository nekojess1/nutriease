package org.nekojess.nutriease.generateRecipe

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.nekojess.nutriease.data.repository.recipe.RecipeRepository
import org.nekojess.nutriease.domain.dto.RecipeDto
import org.nekojess.nutriease.domain.dto.RecipeDto.Ingredient
import org.nekojess.nutriease.domain.dto.RecipesListDto
import org.nekojess.nutriease.ui.generateRecipe.GenerateRecipesViewModel
import org.nekojess.nutriease.util.BaseModelState

@ExperimentalCoroutinesApi
class GenerateRecipesViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val recipeRepository: RecipeRepository = mock()
    private val observerLiveData: Observer<BaseModelState> = mock()
    private lateinit var viewModel: GenerateRecipesViewModel
    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope = TestCoroutineScope(testCoroutineDispatcher)

    @Before
    fun setup() {
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
        testCoroutineScope.cleanupTestCoroutines()
    }

    @Test
    fun whenStartViewModel_shouldCallGetRecipes() = testCoroutineDispatcher.runBlockingTest {
        whenever(recipeRepository.getRecipeData(mapOf("input_text" to "ovo,leite,cenoura"))).thenReturn(
            mockedList()
        )

        viewModel = GenerateRecipesViewModel(recipeRepository)
        viewModel.getRecipes(mapOf("input_text" to "ovo,leite,cenoura"))

        verify(recipeRepository).getRecipeData(mapOf("input_text" to "ovo,leite,cenoura"))
    }

    @Test
    fun whenStartViewModelWithSuccess_shouldReturnListOfRecipes() =
        testCoroutineDispatcher.runBlockingTest {
            whenever(recipeRepository.getRecipeData(mapOf("input_text" to "ovo,leite,cenoura"))).thenReturn(
                mockedList()
            )

            viewModel = GenerateRecipesViewModel(recipeRepository)
            viewModel.recipeList.observeForever(observerLiveData)
            viewModel.getRecipes(mapOf("input_text" to "ovo,leite,cenoura"))

            verify(observerLiveData).onChanged(BaseModelState.success(mockedList()))
        }

    @Test
    fun whenStartViewModelWithError_shouldReturnErrorMessage() =
        testCoroutineDispatcher.runBlockingTest {
            viewModel = GenerateRecipesViewModel(recipeRepository)
            viewModel.recipeList.observeForever(observerLiveData)

            viewModel.getRecipes(mapOf("input_text" to "ovo,leite,cenoura"))

            val value = viewModel.recipeList.value ?: error("No value for viewModel")

            verify(observerLiveData).onChanged(value.error?.let { BaseModelState.error(it) })
        }

    private fun mockedList(): RecipesListDto {
        return RecipesListDto(
            recipes = listOf(
                RecipeDto(
                    nameRecipe = "Omelete de cenoura",
                    ingredientsList = listOf(
                        Ingredient(ingredient = "ovo", measure = "2 unidades"),
                        Ingredient(ingredient = "cenoura", measure = "1 unidade")
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