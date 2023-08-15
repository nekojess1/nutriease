package org.nekojess.nutriease.ui.generateRecipe.adapterRecipe

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_recipe.view.recipe_calorie
import kotlinx.android.synthetic.main.list_item_recipe.view.recipe_difficulty
import kotlinx.android.synthetic.main.list_item_recipe.view.recipe_name
import org.nekojess.nutriease.domain.dto.RecipeDto

class RecipeListItemViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(recipeDto: RecipeDto) {
        itemView.recipe_name.text = recipeDto.nome
        itemView.recipe_calorie.text = recipeDto.caloria
        itemView.recipe_difficulty.text = recipeDto.dificuldade
    }
}