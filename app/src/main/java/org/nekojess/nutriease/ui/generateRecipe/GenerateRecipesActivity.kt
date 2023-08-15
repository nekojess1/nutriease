package org.nekojess.nutriease.ui.generateRecipe

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.nekojess.nutriease.R
import org.nekojess.nutriease.databinding.ActivityGenerateRecipesBinding
import org.nekojess.nutriease.domain.dto.RecipesListDto
import org.nekojess.nutriease.ui.generateRecipe.adapterRecipe.RecipeListAdapter
import org.nekojess.nutriease.util.BaseModelState

class GenerateRecipesActivity : AppCompatActivity() {
    private val viewModel: GenerateRecipesViewModel by viewModel()

    val adapter: RecipeListAdapter by lazy { RecipeListAdapter() }
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
                } else textField.error = "A lista de ingredientes não pode ser vazia" +
                        "\nOs ingredientes devem estar em sequência separados por virgula. \nExemplo: Ovo, leite, cenoura."
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
        adapter.recipes = recipesList.receitas
        binding.loading.root.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE
    }

    private fun statusError(error: Throwable?) {
        binding.loading.root.visibility = View.GONE
        binding.recyclerView.visibility = View.GONE
        Log.i("ERROR", error?.message.toString())
        Toast.makeText(this, getString(R.string.error), Toast.LENGTH_LONG).show()
    }

    private fun statusLoading() {
        binding.loading.root.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE
        binding.groupIngredient.visibility = View.GONE
    }
}