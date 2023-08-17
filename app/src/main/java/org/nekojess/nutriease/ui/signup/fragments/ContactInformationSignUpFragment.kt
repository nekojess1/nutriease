package org.nekojess.nutriease.ui.signup.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import org.nekojess.nutriease.R
import org.nekojess.nutriease.databinding.FragmentContactInformationSignUpBinding
import org.nekojess.nutriease.domain.dto.UserDto

class ContactInformationSignUpFragment : Fragment() {
    private val binding: FragmentContactInformationSignUpBinding by lazy {
        FragmentContactInformationSignUpBinding.inflate(layoutInflater)
    }

    private val args: ContactInformationSignUpFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        configureContinueButton()
        setHeaderConfig()
        return binding.root
    }

    private fun setHeaderConfig() {
        binding.contactInformationSignUpFragmentHeader.setTitle(getString(R.string.register))
        binding.contactInformationSignUpFragmentHeader.setBackButtonListener { findNavController().popBackStack() }
    }


    private fun configureContinueButton() {
        binding.contactInformationSignUpFragmentContinueButton.setOnClickListener {
            val action =
                ContactInformationSignUpFragmentDirections.actionContactInformationSignUpFragmentToSelectPasswordSignUpFragment(
                    getPersonalData()
                )
            Navigation.findNavController(binding.root).navigate(action)
        }
    }

    private fun getPersonalData(): UserDto {
        val personalData = args.personalData
        return UserDto(
            personalData.name,
            personalData.birthday,
            personalData.crn,
            personalData.uf,
            personalData.city,
            binding.contactInformationSignUpFragmentEmailText.text.toString(),
            binding.contactInformationSignUpFragmentPhoneText.text.toString()
        )
    }
}
