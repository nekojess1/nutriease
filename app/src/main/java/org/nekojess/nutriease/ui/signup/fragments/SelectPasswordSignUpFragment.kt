package org.nekojess.nutriease.ui.signup.fragments

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.nekojess.nutriease.R
import org.nekojess.nutriease.databinding.FragmentSelectPasswordSignUpBinding
import org.nekojess.nutriease.domain.dto.LoginDto
import org.nekojess.nutriease.ui.home.HomeActivity
import org.nekojess.nutriease.ui.signup.SignUpViewModel
import org.nekojess.nutriease.util.StringUtils.hashPassword

class SelectPasswordSignUpFragment : Fragment() {

    private val binding: FragmentSelectPasswordSignUpBinding by lazy {
        FragmentSelectPasswordSignUpBinding.inflate(layoutInflater)
    }

    private val viewModel: SignUpViewModel by viewModel()

    private val args: SelectPasswordSignUpFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        configureFinishButton()
        configPasswordStrength()
        setHeaderConfig()
        return binding.root
    }

    private fun setHeaderConfig() {
        binding.contactInformationSignUpFragmentHeader.setTitle(getString(R.string.register))
        binding.contactInformationSignUpFragmentHeader.setBackButtonListener { findNavController().popBackStack() }
    }

    private fun configureFinishButton() {
        binding.selectPasswordSignUpFragmentFinishButton.setOnClickListener {
            setButtonEnable(false)
            createUser()
        }
    }

    private fun createUser() {
        val email = args.personalData.email
        val password =
            binding.selectPasswordSignUpFragmentConfirmPasswordText.text.toString().hashPassword()
        viewModel.saveUser(LoginDto(email, password), args.personalData)
        viewModel.userLiveData.observe(requireActivity()) { signUpResult ->
            if (signUpResult) {
                openHome()
            } else {
                setButtonEnable(true)
            }
        }
    }

    private fun setButtonEnable(isVisible: Boolean) {
        binding.selectPasswordSignUpFragmentFinishButton.isEnabled = isVisible
    }

    private fun openHome() {
        startActivity(Intent(requireContext(), HomeActivity::class.java))
        requireActivity().finish()
    }

    private fun configPasswordStrength() {
        binding.selectPasswordSignUpFragmentConfirmPasswordText.addTextChangedListener(object :
            TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val password = binding.selectPasswordSignUpFragmentPasswordText.text.toString()
                val confirmPassword = s.toString()
                binding.selectPasswordSignUpFragmentFinishButton.isEnabled =
                    verifyPasswordCondition(password, confirmPassword)
            }
        })
    }

    private fun verifyPasswordCondition(password: String, confirmPassword: String): Boolean {
        return binding.selectPasswordSignUpFragmentStrengthBar.checkPasswordConditions(
            password,
            confirmPassword
        )
    }
}
