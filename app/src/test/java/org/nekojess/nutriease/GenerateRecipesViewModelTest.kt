package org.nekojess.nutriease

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
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

    private val useCase: RecipeRepository = mock()
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
    fun whenStartViewModel_shouldCallGetUsers() = testCoroutineDispatcher.runBlockingTest {
        whenever(useCase.getRecipeData(mapOf("input_text" to "ovo, leite, cenoura"))).thenReturn(
            mockedList()
        )
        viewModel = GenerateRecipesViewModel(useCase)
        viewModel.recipeList.observeForever(observerLiveData)
        verify(useCase).getRecipeData(mapOf("input_text" to "ovo, leite, cenoura"))
    }

    @Test
    fun whenStartViewModelWithSuccess_shouldReturnListOfUsers() =
        testCoroutineDispatcher.runBlockingTest {
            whenever(useCase.getRecipeData(mapOf("input_text" to "ovo, leite, cenoura")))

            viewModel = GenerateRecipesViewModel(useCase)
            viewModel.recipeList.observeForever(observerLiveData)
            viewModel.recipeList

            verify(observerLiveData).onChanged(BaseModelState.success(mockedList()))
        }

    @Test
    fun whenStartViewModelWithError_shouldReturnErrorMessage() =
        testCoroutineDispatcher.runBlockingTest {
            viewModel = GenerateRecipesViewModel(useCase)
            viewModel.recipeList.observeForever(observerLiveData)

            viewModel.recipeList

            val value = viewModel.recipeList.value ?: error("No value for viewModel")

            verify(observerLiveData).onChanged(value.error?.let { BaseModelState.error(it) })
        }

    private fun mockedList(): RecipesListDto {
        return RecipesListDto(
            receitas = listOf(
                RecipeDto(
                    nome = "Omelete de cenoura",
                    ingredientesList = listOf(
                        Ingredient(
                            ingrediente = "ovo",
                            medida = "2 unidades"
                        ),
                        Ingredient(
                            ingrediente = "cenoura",
                            medida = "1 unidades"
                        )
                    ),
                    modo_de_preparo = "Rale a cenoura e misture com os ovos batidos. Tempere a gosto e despeje em uma frigideira antiaderente. Cozinhe em fogo baixo até firmar dos dois lados. Sirva quente.",
                    dificuldade = "Dificuldade: Fácil",
                    caloria = "150 Kcal"
                )
            )
        )
    }
}