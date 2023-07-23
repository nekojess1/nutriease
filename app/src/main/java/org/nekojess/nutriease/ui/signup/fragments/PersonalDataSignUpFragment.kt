package org.nekojess.nutriease.ui.signup.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import org.nekojess.nutriease.R
import org.nekojess.nutriease.databinding.FragmentPersonalDataSignUpBinding
import org.nekojess.nutriease.domain.dto.UserDto
import org.nekojess.nutriease.util.DateTextWatcher

class PersonalDataSignUpFragment : Fragment() {
    private val binding: FragmentPersonalDataSignUpBinding by lazy {
        FragmentPersonalDataSignUpBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        configureContinueButton()
        setStatesList()
        setDateChangedListener()
        return binding.root
    }

    private fun setDateChangedListener() {
        binding.personalDataSignUpFragmentBirthdayText.addTextChangedListener(DateTextWatcher())
    }

    private fun setStatesList() {
        val ufs = resources.getStringArray(R.array.states_br)
        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.simple_list_item,
            ufs
        )
        binding.personalDataSignUpFragmentUfText.setAdapter(adapter)
    }

    private fun configureContinueButton() {
        binding.personalDataSignUpFragmentContinueButton.setOnClickListener {
            val action =
                PersonalDataSignUpFragmentDirections.actionPersonalDataSignUpFragmentToContactInformationSignUpFragment(
                    getPersonalData()
                )
            Navigation.findNavController(binding.root).navigate(action)
        }
    }

    private fun getPersonalData(): UserDto {
        return UserDto(
            binding.personalDataSignUpFragmentNameText.text.toString(),
            binding.personalDataSignUpFragmentBirthdayText.text.toString(),
            binding.personalDataSignUpFragmentCrnText.text.toString(),
            binding.personalDataSignUpFragmentUfText.text.toString(),
            binding.personalDataSignUpFragmentCityText.text.toString()
        )
    }

}
