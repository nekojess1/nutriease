package org.nekojess.nutriease.ui.signup.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.nekojess.nutriease.databinding.FragmentSelectPasswordSignUpBinding

class SelectPasswordSignUpFragment : Fragment() {
    private val binding: FragmentSelectPasswordSignUpBinding by lazy {
        FragmentSelectPasswordSignUpBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        configureFinishButton()
        return binding.root
    }

    private fun configureFinishButton() {
        binding.selectPasswordSignUpFragmentFinishButton.setOnClickListener {

        }
    }

}