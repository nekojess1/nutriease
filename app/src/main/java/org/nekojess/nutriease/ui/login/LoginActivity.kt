package org.nekojess.nutriease.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.nekojess.nutriease.R
import org.nekojess.nutriease.databinding.ActivityLoginBinding
import org.nekojess.nutriease.domain.dto.LoginDto
import org.nekojess.nutriease.ui.home.HomeActivity
import org.nekojess.nutriease.ui.signup.SignUpActivity
import org.nekojess.nutriease.util.StringUtils.hashPassword
import org.nekojess.nutriease.util.VerificationUtils.isValidEmail
import org.nekojess.nutriease.util.VerificationUtils.isEmptyText


class LoginActivity : AppCompatActivity() {

    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private lateinit var googleSignInClient: GoogleSignInClient

    private val binding: ActivityLoginBinding by lazy { ActivityLoginBinding.inflate(layoutInflater) }

    private val viewModel: LoginViewModel by viewModel()

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
            setLoginWithGoogle()
        }
    }

    private fun setLoginWithGoogle() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        binding.signInButton.setOnClickListener { signInGoogle() }
    }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                handleResults(task)
            }
        }

    private fun signInGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }

    private fun handleResults(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)
            if (account != null) {
                updateUI(account)
            }
        } catch (e: ApiException) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) openHome()
            else Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
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
        viewModel.authUser(LoginDto(email, password))
        viewModel.loginResultLiveData.observe(this) { loginResult ->
            if (loginResult) {
                openHome()
            } else {
                setButtonEnable(true)
            }
        }
    }

    private fun openHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}
