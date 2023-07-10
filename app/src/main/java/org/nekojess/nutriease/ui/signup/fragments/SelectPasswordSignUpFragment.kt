package org.nekojess.nutriease.ui.signup.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.nekojess.nutriease.databinding.FragmentSelectPasswordSignUpBinding

class SelectPasswordSignUpFragment : Fragment() {
    private val binding: FragmentSelectPasswordSignUpBinding by lazy {
        FragmentSelectPasswordSignUpBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        configureFinishButton()
        configPasswordStrength()
        return binding.root
    }

    private fun configPasswordStrength() {
        binding.selectPasswordSignUpFragmentConfirmPasswordText.addTextChangedListener(object :
            TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val password = s.toString()
                binding.selectPasswordSignUpFragmentStrengthBar.verifyPasswordStrength(password)
            }
        })
    }

    private fun configureFinishButton() {
        binding.selectPasswordSignUpFragmentFinishButton.setOnClickListener {

        }
    }
}
