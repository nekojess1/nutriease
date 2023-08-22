package org.nekojess.nutriease.ui.createPatient.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.nekojess.nutriease.R
import org.nekojess.nutriease.databinding.FragmentCreatePatientNutriInfoBinding
import org.nekojess.nutriease.domain.dto.PatientDto
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
        setPafList()
        setHeaderConfig()
        return binding.root
    }

    private fun setHeaderConfig() {
        binding.createPatientNutriInfoFragmentHeader.setTitle(getString(R.string.register_patient))
        binding.createPatientNutriInfoFragmentHeader.setBackButtonListener { findNavController().popBackStack() }
    }

    private fun setRegisterPatientButton() {
        binding.createPatientFragmentRegisterButton.setOnClickListener {
            setButtonEnable(false)
            savePatient()
        }
    }

    private fun savePatient() {
        viewModel.savePatientData(getPatientData())
        viewModel.patientLiveData.observe(requireActivity()) { registerResult ->
            if (registerResult) {
                requireActivity().finish()
            } else {
                setButtonEnable(true)
            }
        }
    }

    private fun getPatientData(): PatientDto {
        val patientData = args.patientData
        return PatientDto(
            name = patientData.name,
            birthday = patientData.birthday,
            genre = patientData.genre,
            uf = patientData.uf,
            city = patientData.city,
            email = patientData.email,
            phone = patientData.phone,
            paf = binding.createPatientFragmentPafText.text.toString(),
            weight = binding.createPatientFragmentWeightText.text.toString(),
            height = binding.createPatientFragmentHeightText.text.toString(),
            target = binding.createPatientFragmentTargetText.text.toString(),
            foodRestriction = binding.createPatientFragmentFoodRestrictionText.text.toString(),
            foodPreference = binding.createPatientFragmentFoodPreferenceText.text.toString(),
            patientPhoto = patientData.patientPhoto
        )
    }

    private fun setPafList() {
        val ufs = resources.getStringArray(R.array.paf)
        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.simple_list_item,
            ufs
        )
        binding.createPatientFragmentPafText.setAdapter(adapter)
    }

    private fun setButtonEnable(isVisible: Boolean) {
        binding.createPatientFragmentRegisterButton.isEnabled = isVisible
    }
}
