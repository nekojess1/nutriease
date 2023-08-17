package org.nekojess.nutriease.ui.generateRecipe

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.nekojess.nutriease.R
import org.nekojess.nutriease.databinding.ActivityGenerateRecipesBinding
import org.nekojess.nutriease.databinding.FragmentRecipeDetailBottomSheetBinding
import org.nekojess.nutriease.domain.dto.RecipeDto
import org.nekojess.nutriease.domain.dto.RecipesListDto
import org.nekojess.nutriease.ui.generateRecipe.adapterRecipe.RecipeClickListener
import org.nekojess.nutriease.ui.generateRecipe.adapterRecipe.RecipeListAdapter
import org.nekojess.nutriease.util.BaseModelState

class GenerateRecipesActivity : AppCompatActivity(), RecipeClickListener {
    private val viewModel: GenerateRecipesViewModel by viewModel()

    private val adapter: RecipeListAdapter by lazy { RecipeListAdapter(this) }
    private val binding: ActivityGenerateRecipesBinding by lazy {
        ActivityGenerateRecipesBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setAdapter()
        setObservable()
        setGenerateRecipesButtonClick()
    }

    private fun setGenerateRecipesButtonClick() {
        with(binding) {
            elevatedButton.setOnClickListener {
                if (textInput.text.isNullOrEmpty().not()) {
                    viewModel.getRecipes(mapOf("input_text" to textInput.text.toString()))
                } else textField.error =
                    getString(R.string.textField_error).plus(getString(R.string.textField_helperText))
            }
        }

    }

    private fun setObservable() {
        viewModel.recipeList.observe(this) {
            when (it?.status) {
                BaseModelState.STATUS.ERROR -> {
                    statusError(it.error)
                }

                BaseModelState.STATUS.SUCCESS -> {
                    it.data
                    it.data?.let { it1 -> statusSuccess(it1) }
                }

                BaseModelState.STATUS.LOADING -> {
                    statusLoading()
                }

                else -> Unit
            }
        }
    }

    private fun setAdapter() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

    }

    private fun statusSuccess(recipesList: RecipesListDto) {
        adapter.recipes = recipesList.recipes
        binding.loading.root.visibility = View.GONE
        binding.groupIngredient.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE
    }

    private fun statusError(error: Throwable?) {
        binding.loading.root.visibility = View.GONE
        binding.recyclerView.visibility = View.GONE
        binding.groupIngredient.visibility = View.VISIBLE
        Log.i("ERROR", error?.message.toString())
        Toast.makeText(this, getString(R.string.error), Toast.LENGTH_LONG).show()
    }

    private fun statusLoading() {
        binding.loading.root.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE
        binding.groupIngredient.visibility = View.GONE
    }

    override fun onRecipeClickListener(recipeDto: RecipeDto) {
        val dialog = BottomSheetDialog(this)
        val bindingRecipeDetail: FragmentRecipeDetailBottomSheetBinding by lazy {
            FragmentRecipeDetailBottomSheetBinding.inflate(
                layoutInflater
            )
        }
        with(bindingRecipeDetail) {
            tvRecipeName.text = recipeDto.nameRecipe
            tvRecipeMethodDescription.text = recipeDto.method
            var listIngredient = mutableListOf<String>()
            recipeDto.ingredientsList.map {
                listIngredient.add(it.ingredient + " - " + it.measure)
            }
            tvRecipeIngredientDescription.text = listIngredient.toString()
            tvCalorieDescription.text = recipeDto.calorie
            tvProteinDescription.text = recipeDto.protein
            tvCarbohydrateDescription.text = recipeDto.carbohydrate
            btnClose.setOnClickListener { dialog.dismiss() }
            btnFavorite.setOnClickListener {
                Toast.makeText(
                   this@GenerateRecipesActivity,
                    getString(R.string.bottom_sheet_recipe_detail_favorite_button),
                    Toast.LENGTH_SHORT
                ).show()
            }
            btnShare.setOnClickListener { Toast.makeText(
                this@GenerateRecipesActivity,
                getString(R.string.bottom_sheet_recipe_detail_share_button),
                Toast.LENGTH_SHORT
            ).show() }
        }

        dialog.setContentView(bindingRecipeDetail.root)
        dialog.show()
    }
}