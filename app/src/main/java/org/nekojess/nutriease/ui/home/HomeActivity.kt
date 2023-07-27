package org.nekojess.nutriease.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import org.nekojess.nutriease.databinding.ActivityHomeBinding
import org.nekojess.nutriease.ui.login.LoginActivity

class HomeActivity : AppCompatActivity() {
    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val binding: ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            auth.signOut()
            openLogin()
        }
    }

    private fun openLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}