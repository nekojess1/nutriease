package org.nekojess.nutriease.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import org.nekojess.nutriease.R
import org.nekojess.nutriease.databinding.ActivityHomeBinding
import org.nekojess.nutriease.ui.login.LoginActivity
import org.nekojess.nutriease.util.StringUtils.toHtml
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.nekojess.nutriease.domain.dto.HomeDto
import org.nekojess.nutriease.ui.createPatient.CreatePatientActivity

class HomeActivity : AppCompatActivity() {
    private val binding: ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    private val viewModel: HomeViewModel by viewModel()

    private val drawerLayout: DrawerLayout by lazy {
        binding.drawerLayout
    }

    private val navigationView: NavigationView by lazy {
        binding.navigationView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setViewModel()
        setImageClick()
        configLateralMenu()
        setRegisterUserButton()
    }

    override fun onResume() {
        super.onResume()
        setViewModel()
    }

    private fun setRegisterUserButton() {
        binding.activityHomeRegisterPatient.setOnClickListener {
            startActivity(Intent(this, CreatePatientActivity::class.java))
        }
    }

    private fun configLateralMenu() {
        val toggle =
            ActionBarDrawerToggle(this, drawerLayout, R.string.exit, R.string.create_new_diet)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        setMenuItemListener()
    }

    private fun setMenuItemListener() {
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.exit -> {
                    viewModel.signOutUser()
                    openLogin()
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    private fun setImageClick() {
        binding.activityHomeUserImage.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }

    private fun openLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun setViewModel() {
        viewModel.getUserData()
        viewModel.userLiveData.observe(this) { data ->
            setUserData(data)
        }
    }

    private fun setUserData(data: HomeDto) {
        binding.activityHomeUserName.text =
            getString(R.string.home_activity_user_name, data.user?.name).toHtml()
        binding.activityHomePatientList.adapter = HomePatientsAdapter(data.patients)
        binding.navigationView.findViewById<TextView>(R.id.nav_header_user_name).text =
            data.user?.name
    }

}
