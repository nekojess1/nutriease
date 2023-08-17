package org.nekojess.nutriease.ui.generateRecipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.nekojess.nutriease.databinding.FragmentRecipeDetailBottomSheetBinding

class RecipeDetailBottomSheet : BottomSheetDialogFragment() {
    private val binding: FragmentRecipeDetailBottomSheetBinding by lazy {
        FragmentRecipeDetailBottomSheetBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}