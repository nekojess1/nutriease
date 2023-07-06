package org.nekojess.nutriease.ui.signup.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.nekojess.nutriease.R
import org.nekojess.nutriease.databinding.ActivityOnboardingBinding
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
        return binding.root
    }

}