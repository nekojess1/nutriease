package org.nekojess.nutriease.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import org.nekojess.nutriease.databinding.ActivityLoginBinding
import org.nekojess.nutriease.ui.onboarding.OnboardingActivity


class LoginActivity : AppCompatActivity() {
    private val mAuth: FirebaseAuth? by lazy {
        FirebaseAuth.getInstance()
    }

    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.loginActivityEnterButton.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val email = binding.loginActivityEmailText.text.toString()
        val password = binding.loginActivityPasswordText.text.toString()
        mAuth?.signInWithEmailAndPassword(email, password)?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                startActivity(Intent(this, OnboardingActivity::class.java))
                finish()
            }
        }?.addOnFailureListener { exception ->
            Toast.makeText(this, exception.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }
}