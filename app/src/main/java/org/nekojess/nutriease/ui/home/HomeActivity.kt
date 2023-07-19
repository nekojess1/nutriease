package org.nekojess.nutriease.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationBarView
import org.nekojess.nutriease.R
import org.nekojess.nutriease.databinding.ActivityHomeBinding
import org.nekojess.nutriease.ui.configuration.ConfigurationFragment

class HomeActivity : AppCompatActivity() {
    private val binding: ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    private val onItemSelectedListener =
        NavigationBarView.OnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.settings -> {
                    val configurationFragment = ConfigurationFragment()
                    replaceFragment(configurationFragment)
                    return@OnItemSelectedListener true
                }
                else -> return@OnItemSelectedListener false
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.bottomNavigation.setOnItemSelectedListener(onItemSelectedListener)
        replaceFragment(ConfigurationFragment())
        binding.bottomNavigation.selectedItemId = R.id.settings
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.content_frame, fragment)
            .commit()
    }
}