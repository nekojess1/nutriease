package org.nekojess.nutriease.ui.generateRecipe.adapterRecipe

import androidx.recyclerview.widget.RecyclerView
import org.nekojess.nutriease.databinding.ListItemRecipeBinding
import org.nekojess.nutriease.domain.dto.RecipeDto

class RecipeListItemViewHolder(
    private val itemBinding: ListItemRecipeBinding
) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(recipeDto: RecipeDto) {
        with(itemBinding){
            recipeName.text = recipeDto.nameRecipe
            recipeCalorie.text = recipeDto.calorie
            recipeDifficulty.text = recipeDto.difficulty
        }
    }
}