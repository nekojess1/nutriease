package org.nekojess.nutriease.ui.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayoutMediator
import org.nekojess.nutriease.R
import org.nekojess.nutriease.databinding.BottomSheetPatientProfileBinding
import org.nekojess.nutriease.domain.dto.PatientDto
import org.nekojess.nutriease.util.StringUtils.EMPTY_STRING

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
        setTabLayout()
        setCloseIcon()
        return binding.root
    }

    private fun setCloseIcon() {
        binding.patientProfileCloseIcon.setOnClickListener {
            this.dismiss()
        }
    }

    private fun setTabLayout() {
        val adapter = PatientProfileAdapter(requireActivity(), patientData)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.patientProfileTabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.personal_info)
                1 -> getString(R.string.nutritional_info)
                else -> EMPTY_STRING
            }
        }.attach()
    }

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    private fun setText() {
        binding.patientProfileName.text = patientData.name
        binding.patientProfileLocation.text =
            getString(R.string.patient_profile_locality, patientData.city, patientData.uf)
    }

    private fun setUserImage() {
        if (patientData.patientPhoto.isNotEmpty()) {
            Glide.with(requireContext())
                .load(patientData.patientPhoto)
                .into(binding.patientProfilePhoto)
        }
    }

}
