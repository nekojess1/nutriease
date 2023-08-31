package org.nekojess.nutriease.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import org.nekojess.nutriease.R
import org.nekojess.nutriease.databinding.ActivityHomeBinding
import org.nekojess.nutriease.ui.login.LoginActivity
import org.nekojess.nutriease.util.StringUtils.toHtml
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.nekojess.nutriease.domain.dto.HomeDto
import org.nekojess.nutriease.domain.dto.PatientDto
import org.nekojess.nutriease.ui.components.PatientProfileBottomSheet
import org.nekojess.nutriease.ui.createPatient.CreatePatientActivity
import org.nekojess.nutriease.ui.generateRecipe.GenerateRecipesActivity
import org.nekojess.nutriease.ui.patientList.PatientListActivity
import org.nekojess.nutriease.util.PatientListUtil.sortByName

class HomeActivity : AppCompatActivity(), HomePatientsAdapter.PatientClickListener {

    private var patientList: List<PatientDto> = emptyList()

    private val binding: ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    private val viewModel: HomeViewModel by viewModel()

    private val drawerLayout: DrawerLayout by lazy {
        binding.drawerLayout
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setViewModel()
        setUserImageClick()
        configLateralMenu()
        setRegisterUserButton()
        setGenerateRecipesButton()
        configSeeMorePatientsButton()
    }

    private fun configSeeMorePatientsButton() {
        binding.activityHomePatientSeeMoreBtn.setOnClickListener {
            startActivity(PatientListActivity.newInstance(this, patientList))
        }
    }

    override fun onResume() {
        super.onResume()
        setViewModel()
    }

    private fun setGenerateRecipesButton() {
        binding.activityHomeGenerateRecipes.setOnClickListener {
            startActivity(Intent(this, GenerateRecipesActivity::class.java))
        }
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
        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
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

    private fun setUserImageClick() {
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
        startLoading()
        viewModel.getUserData()
        viewModel.userLiveData.observe(this) { data ->
            binding.activityHomeContainer.isVisible = true
            setUserData(data)
            stopLoading()
        }
    }

    private fun stopLoading() {
        binding.shimmerLayout.stopShimmer()
        binding.shimmerLayout.isVisible = false
    }

    private fun startLoading() {
        binding.shimmerLayout.startShimmer()
        binding.shimmerLayout.isVisible = true
    }

    private fun setUserData(data: HomeDto) {
        binding.activityHomeUserName.text =
            getString(R.string.home_activity_user_name, data.user?.name).toHtml()
        binding.navigationView.getHeaderView(0).findViewById<TextView>(R.id.nav_header_user_name).text =
            data.user?.name
        patientList = data.patients
        setPatientList(data)
    }

    private fun setPatientList(data: HomeDto) {
        if(data.patients.isEmpty()){
            setPatientListVisibilityConfig(true)
        } else {
            val sublistSize = minOf(6, data.patients.size)
            val adapter = HomePatientsAdapter(data.patients.sortByName().subList(0, sublistSize))
            adapter.setPatientClickListener(this)
            binding.activityHomePatientList.adapter = adapter
            setPatientListVisibilityConfig(false)
        }
    }

    private fun setPatientListVisibilityConfig(isVisible: Boolean) {
        binding.activityHomeEmptyClient.emptyClientContainer.isVisible = isVisible
        binding.activityHomePatientList.isVisible = !isVisible
    }

    override fun onPatientClick(patient: PatientDto) {
        val bottomSheetFragment = PatientProfileBottomSheet(patient)
        bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
    }
}
