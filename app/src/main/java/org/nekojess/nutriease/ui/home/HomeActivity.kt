package org.nekojess.nutriease.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.nekojess.nutriease.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private val binding: ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

}