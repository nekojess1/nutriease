package org.nekojess.nutriease.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import org.nekojess.nutriease.R
import org.nekojess.nutriease.databinding.StrengthPasswordComponentBinding

class StrengthPassword @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: StrengthPasswordComponentBinding = StrengthPasswordComponentBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    fun checkPasswordConditions(password: String, confirmPassword: String): Boolean {
        val hasUppercase = confirmPassword.any { it.isUpperCase() }
        val hasLowercase = confirmPassword.any { it.isLowerCase() }
        val hasNumbers = confirmPassword.any { it.isDigit() }
        val has8Character = confirmPassword.length > 8
        val isEqualsPasswords = confirmPassword == password
        val hasSpecialChars = hasSpecialChars(confirmPassword)

        binding.strengthPasswordComponentCondition1Icon.setImageResource(getImageResource(has8Character))
        binding.strengthPasswordComponentCondition2Icon.setImageResource(getImageResource(hasUppercase && hasLowercase))
        binding.strengthPasswordComponentCondition3Icon.setImageResource(getImageResource(hasNumbers))
        binding.strengthPasswordComponentCondition4Icon.setImageResource(getImageResource(hasSpecialChars))
        binding.strengthPasswordComponentCondition5Icon.setImageResource(getImageResource(isEqualsPasswords))

        return hasUppercase && hasLowercase && hasNumbers && has8Character && isEqualsPasswords && hasSpecialChars
    }

    private fun hasSpecialChars(password: String): Boolean {
        val specialChars = "!@#$%^&*()-=_+[]{}|;:,.<>/?"
        return password.any { specialChars.contains(it) }
    }
    private fun getImageResource(condition: Boolean): Int =
        if (condition) R.drawable.done_icon else R.drawable.close_red_ic

}
