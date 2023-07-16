package org.nekojess.nutriease.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import org.nekojess.nutriease.databinding.ActivityLoginBinding
import org.nekojess.nutriease.ui.home.HomeActivity
import org.nekojess.nutriease.ui.signup.SignUpActivity
import org.nekojess.nutriease.util.StringUtils.hashPassword
import org.nekojess.nutriease.util.VerificationUtils.isValidEmail
import org.nekojess.nutriease.util.VerificationUtils.isEmptyText


class LoginActivity : AppCompatActivity() {
    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verifyIfUserIsConnected()
        setContentView(binding.root)
    }

    private fun verifyIfUserIsConnected() {
        if (auth.currentUser != null) {
            openHome()
        } else {
            setLoginButton()
            setSignUpButton()
        }
    }

    private fun setLoginButton() {
        binding.loginActivityEnterButton.setOnClickListener {
            setButtonEnable(false)
            if (checkValidEmail() && checkValidPassword()) {
                login()
            } else {
                setButtonEnable(true)
            }
        }
    }

    private fun setButtonEnable(isVisible: Boolean) {
        binding.loginActivityEnterButton.isEnabled = isVisible
    }

    private fun setSignUpButton() {
        binding.loginActivitySignUpButton.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    private fun checkValidEmail() = binding.loginActivityEmail.isValidEmail()
    private fun checkValidPassword() = binding.loginActivityPassword.isEmptyText()

    private fun login() {
        val email = binding.loginActivityEmailText.text.toString().trim()
        val password = binding.loginActivityPasswordText.text.toString().hashPassword()

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                openHome()
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(this, exception.localizedMessage, Toast.LENGTH_LONG).show()
            setButtonEnable(true)
        }
    }

    private fun openHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}
