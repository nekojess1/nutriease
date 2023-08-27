package org.nekojess.nutriease.ui.components

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PatientProfileAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PatientProfilePersonalData()
            1 -> PatientProfileNutriInformation()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
}

