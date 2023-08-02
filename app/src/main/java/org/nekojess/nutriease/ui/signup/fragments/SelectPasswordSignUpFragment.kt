package org.nekojess.nutriease.ui.signup.fragments

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.nekojess.nutriease.databinding.FragmentSelectPasswordSignUpBinding
import org.nekojess.nutriease.ui.home.HomeActivity
import org.nekojess.nutriease.util.StringUtils.hashPassword

class SelectPasswordSignUpFragment : Fragment() {
    private val binding: FragmentSelectPasswordSignUpBinding by lazy {
        FragmentSelectPasswordSignUpBinding.inflate(layoutInflater)
    }

    private val fireStore: FirebaseFirestore by lazy {
        Firebase.firestore
    }

    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val args: SelectPasswordSignUpFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        configureFinishButton()
        configPasswordStrength()
        return binding.root
    }

    private fun configureFinishButton() {
        binding.selectPasswordSignUpFragmentFinishButton.setOnClickListener {
            createUser()
        }
    }

    private fun createUser() {
        val email = args.personalData.email
        val password =
            binding.selectPasswordSignUpFragmentConfirmPasswordText.text.toString().hashPassword()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    saveUserData()
                } else {
                    Toast.makeText(context, "Algo deu errado, tente novamente", Toast.LENGTH_LONG)
                        .show()
                }
            }
    }

    private fun saveUserData() {
        val user = auth.currentUser
        val userId = user?.uid

        if (userId != null) {
            val personalData = args.personalData
            fireStore.collection("users")
                .document(userId)
                .set(personalData)
                .addOnSuccessListener {
                    openHome()
                }
                .addOnFailureListener { _ ->
                    Toast.makeText(
                        context,
                        "Algo deu errado, tente novamente",
                        Toast.LENGTH_LONG
                    ).show()
                }
        }
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
