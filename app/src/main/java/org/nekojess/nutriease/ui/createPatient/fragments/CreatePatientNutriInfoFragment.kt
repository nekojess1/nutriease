package org.nekojess.nutriease.ui.createPatient.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.nekojess.nutriease.databinding.FragmentCreatePatientNutriInfoBinding
import org.nekojess.nutriease.ui.createPatient.CreatePatientViewModel

class CreatePatientNutriInfoFragment : Fragment() {
    private val binding: FragmentCreatePatientNutriInfoBinding by lazy {
        FragmentCreatePatientNutriInfoBinding.inflate(layoutInflater)
    }

    private val args: CreatePatientNutriInfoFragmentArgs by navArgs()

    private val viewModel: CreatePatientViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setRegisterPatientButton()
        return binding.root
    }

    private fun setRegisterPatientButton() {
        binding.createPatientActivityContinueButton.setOnClickListener {
            setButtonEnable(false)
            viewModel.savePatientData(args.patientData)
            viewModel.patientLiveData.observe(requireActivity()) { registerResult ->
                if (registerResult) {
                    requireActivity().finish()
                } else {
                    setButtonEnable(true)
                }
            }
        }
    }

    private fun setButtonEnable(isVisible: Boolean) {
        binding.createPatientActivityContinueButton.isEnabled = isVisible
    }
}
