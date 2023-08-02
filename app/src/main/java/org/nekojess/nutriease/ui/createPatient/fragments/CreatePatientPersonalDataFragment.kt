package org.nekojess.nutriease.ui.createPatient.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import org.nekojess.nutriease.R
import org.nekojess.nutriease.databinding.FragmentCreatePatientPersonalDataBinding
import org.nekojess.nutriease.domain.dto.PatientDto
import org.nekojess.nutriease.util.DateTextWatcher
import org.nekojess.nutriease.util.VerificationUtils.isNotEmptyText

class CreatePatientPersonalDataFragment : Fragment() {
    private val binding: FragmentCreatePatientPersonalDataBinding by lazy {
        FragmentCreatePatientPersonalDataBinding.inflate(layoutInflater)
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
        binding.createPatientActivityBirthdayText.addTextChangedListener(DateTextWatcher())
    }

    private fun setStatesList() {
        val ufs = resources.getStringArray(R.array.states_br)
        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.simple_list_item,
            ufs
        )
        binding.createPatientActivityUfText.setAdapter(adapter)
    }

    private fun configureContinueButton() {
        binding.createPatientContinueButton.setOnClickListener {
            if (binding.createPatientActivityName.isNotEmptyText()) {
                val action =
                    CreatePatientPersonalDataFragmentDirections
                        .actionCreatePatientPersonalDataFragmentToCreatePatientNutriInfoFragment(
                        getPersonalData()
                    )
                Navigation.findNavController(binding.root).navigate(action)
            }
        }
    }

    private fun getPersonalData(): PatientDto {
        return PatientDto(
            name = binding.createPatientActivityNameText.text.toString(),
            binding.createPatientActivityBirthdayText.text.toString(),
            binding.createPatientActivityCrnText.text.toString(),
            binding.createPatientActivityUfText.text.toString(),
            binding.createPatientActivityCityText.text.toString()
        )
    }
}
