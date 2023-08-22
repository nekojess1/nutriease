package org.nekojess.nutriease.ui.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.nekojess.nutriease.R
import org.nekojess.nutriease.databinding.BottomSheetPatientProfileBinding
import org.nekojess.nutriease.domain.dto.PatientDto

class PatientProfileBottomSheet(private val patientData: PatientDto) : BottomSheetDialogFragment() {

    private val binding: BottomSheetPatientProfileBinding by lazy {
        BottomSheetPatientProfileBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setText()
        setUserImage()
        return binding.root
    }

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    private fun setText() {
        binding.patientProfileName.text = patientData.name
    }

    private fun setUserImage() {
        if (patientData.patientPhoto.isNotEmpty()) {
            Glide.with(requireContext())
                .load(patientData.patientPhoto)
                .into(binding.patientProfilePhoto)
        }
    }

}
