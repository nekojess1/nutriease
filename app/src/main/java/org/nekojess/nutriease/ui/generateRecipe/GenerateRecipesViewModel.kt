package org.nekojess.nutriease.ui.generateRecipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.nekojess.nutriease.data.repository.recipe.RecipeRepository
import org.nekojess.nutriease.util.BaseModelState

class GenerateRecipesViewModel(private val recipeRepository: RecipeRepository) : ViewModel() {

    private val _recipeList = MutableLiveData<BaseModelState>()
    val recipeList: LiveData<BaseModelState> = _recipeList

    fun getRecipes(ingredients: Map<String,String>) {

        _recipeList.value = BaseModelState.loading()
        viewModelScope.launch {

            try {
                _recipeList.value = BaseModelState.success(recipeRepository.getRecipeData(ingredients))

            } catch (e: Exception) {
                _recipeList.value = BaseModelState.error(e)
            }
        }
    }
}