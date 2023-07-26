package org.nekojess.nutriease.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import org.nekojess.nutriease.R
import org.nekojess.nutriease.databinding.ActivityHomeBinding
import org.nekojess.nutriease.util.StringUtils.toHtml

class HomeActivity : AppCompatActivity() {
    private val binding: ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setViewModel()
        binding.activityHomeShimmer.startShimmerAnimation()
    }

    private fun setViewModel() {
        viewModel.getUserData()
        viewModel.userLiveData.observe(this) { data ->
            binding.activityHomeUserName.text =
                getString(R.string.home_activity_user_name, data.user?.name).toHtml()
            binding.activityHomePatientList.adapter = HomePatientsAdapter(data.patients)
            binding.activityHomeShimmer.stopShimmerAnimation()
        }
    }

}
