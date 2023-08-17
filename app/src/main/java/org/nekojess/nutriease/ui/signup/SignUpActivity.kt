package org.nekojess.nutriease.ui.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.nekojess.nutriease.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private val binding: ActivitySignUpBinding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}