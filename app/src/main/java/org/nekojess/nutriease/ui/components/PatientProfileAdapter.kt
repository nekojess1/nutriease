package org.nekojess.nutriease.ui.components

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.nekojess.nutriease.domain.dto.PatientDto

class PatientProfileAdapter(fragmentActivity: FragmentActivity, private val patientDto: PatientDto) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PatientProfilePersonalData.newInstance(patientDto)
            1 -> PatientProfileNutriInformation.newInstance(patientDto)
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
}

