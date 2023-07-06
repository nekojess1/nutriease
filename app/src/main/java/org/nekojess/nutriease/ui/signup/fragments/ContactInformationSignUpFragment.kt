package org.nekojess.nutriease.ui.signup.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import org.nekojess.nutriease.R
import org.nekojess.nutriease.databinding.FragmentPersonalDataSignUpBinding

class ContactInformationSignUpFragment : Fragment() {
    private val binding: FragmentPersonalDataSignUpBinding by lazy {
        FragmentPersonalDataSignUpBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        configureContinueButton()
        return binding.root
    }

    private fun configureContinueButton() {
        binding.personalDataSignUpFragmentContinueButton.setOnClickListener {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_contactInformationSignUpFragment_to_selectPasswordSignUpFragment)
        }
    }

}