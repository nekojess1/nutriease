package org.nekojess.nutriease.ui.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.nekojess.nutriease.databinding.FragmentPatientProfileNutriInfoBinding

class PatientProfileNutriInformation : Fragment() {
    private val binding: FragmentPatientProfileNutriInfoBinding by lazy {
        FragmentPatientProfileNutriInfoBinding.inflate(layoutInflater)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return binding.root
    }
}
