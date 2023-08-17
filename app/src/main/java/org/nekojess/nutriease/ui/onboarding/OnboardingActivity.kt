package org.nekojess.nutriease.ui.onboarding

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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

    private val sharedPreferences : SharedPreferences by lazy {
        getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verifyOnboardingIsComplete()
        setContentView(binding.root)
    }

    private fun verifyOnboardingIsComplete() {
        val onboardingCompleted = sharedPreferences.getBoolean("onboarding_completed", false)
        if (!onboardingCompleted) {
            setViewPager()
        } else {
            openLoginActivity()
        }
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
            onClickListener = {
                setContinueButtonClick()
            }
        )
    )

    private fun setContinueButtonClick() {
        openLoginActivity()
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean("onboarding_completed", true)
        editor.apply()
    }

    private fun openLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}
