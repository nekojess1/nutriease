package org.nekojess.nutriease.ui.configuration

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import org.nekojess.nutriease.databinding.FragmentConfigurationBinding
import org.nekojess.nutriease.ui.login.LoginActivity

class ConfigurationFragment : Fragment() {
    private val binding: FragmentConfigurationBinding by lazy {
        FragmentConfigurationBinding.inflate(layoutInflater)
    }

    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        configSignOutButton()
        return binding.root
    }

    private fun configSignOutButton() {
        binding.button.setOnClickListener {
            auth.signOut()
            openLogin()
        }
    }

    private fun openLogin() {
        startActivity(Intent(requireContext(), LoginActivity::class.java))
        requireActivity().finish()
    }
}
