package org.nekojess.nutriease.ui.generateRecipe.adapterRecipe

import androidx.recyclerview.widget.DiffUtil
import org.nekojess.nutriease.domain.dto.RecipeDto

class RecipeListDiffCallback(
    private val oldList: List<RecipeDto>,
    private val newList: List<RecipeDto>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].nome == newList[newItemPosition].nome
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true
    }
}