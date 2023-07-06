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
        setStatesDropDown()
        return binding.root
    }

    private fun setStatesDropDown() {
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
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_personalDataSignUpFragment_to_contactInformationSignUpFragment)
        }
    }

}
