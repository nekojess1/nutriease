package org.nekojess.nutriease.ui.generateRecipe.adapterRecipe

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import org.nekojess.nutriease.R
import org.nekojess.nutriease.domain.dto.RecipeDto

class RecipeListAdapter : RecyclerView.Adapter<RecipeListItemViewHolder>() {

    var recipes = emptyList<RecipeDto>()
        set(value) {
            val result = DiffUtil.calculateDiff(
                RecipeListDiffCallback(
                    field,
                    value
                )
            )
            result.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeListItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_recipe, parent, false)

        return RecipeListItemViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: RecipeListItemViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    override fun getItemCount(): Int = recipes.size
}