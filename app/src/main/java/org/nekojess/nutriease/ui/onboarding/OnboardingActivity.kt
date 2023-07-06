package org.nekojess.nutriease.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.nekojess.nutriease.R
import org.nekojess.nutriease.databinding.ActivityOnboardingBinding
import org.nekojess.nutriease.domain.dto.OnboardingDto
import org.nekojess.nutriease.ui.login.LoginActivity

class OnboardingActivity : AppCompatActivity() {

    private val binding: ActivityOnboardingBinding by lazy {
        ActivityOnboardingBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setViewPager()
    }

    private fun setViewPager() {
        binding.onboardingViewPager.adapter = OnboardingAdapter(getOnboardItems())
        binding.onboardingCircleIndicator.setViewPager(binding.onboardingViewPager)
    }

    private fun getOnboardItems() = listOf(
        OnboardingDto(
            getString(R.string.onboarding_first_frame_title),
            getString(R.string.onboarding_first_frame_description),
            R.drawable.ic_onboarding_doctor
        ),
        OnboardingDto(
            getString(R.string.onboarding_second_frame_title),
            getString(R.string.onboarding_second_frame_description),
            R.drawable.ic_onboarding_diet
        ),
        OnboardingDto(
            getString(R.string.onboarding_third_frame_title),
            imageId = R.drawable.ic_onboarding_woman,
            onClickListener = { openLoginActivity() }
        )
    )

    private fun openLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}
