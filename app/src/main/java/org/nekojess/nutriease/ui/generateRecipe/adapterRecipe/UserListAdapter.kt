package org.nekojess.nutriease.ui.generateRecipe.adapterRecipe

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import org.nekojess.nutriease.databinding.ListItemRecipeBinding
import org.nekojess.nutriease.domain.dto.RecipeDto

class RecipeListAdapter(private val recipeClickListener: RecipeClickListener) :
    RecyclerView.Adapter<RecipeListItemViewHolder>() {

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
        return RecipeListItemViewHolder(
            itemBinding = ListItemRecipeBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecipeListItemViewHolder, position: Int) {
        holder.bind(recipes[position])
        holder.itemView.setOnClickListener {
            recipeClickListener.onRecipeClickListener(recipes[position])
        }
    }

    override fun getItemCount(): Int = recipes.size
}

interface RecipeClickListener {
    fun onRecipeClickListener(recipeDto: RecipeDto)
}