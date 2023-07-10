package org.nekojess.nutriease.ui.components

import android.content.Context
import android.util.AttributeSet
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import org.nekojess.nutriease.R

class StrengthBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ProgressBar(context, attrs, defStyleAttr) {

    fun verifyPasswordStrength(password: String) {
        updateStrengthBarColor(evaluatePasswordStrength(password))
    }

    private fun evaluatePasswordStrength(password: String): PasswordStrength {
        val hasUppercase = password.any { it.isUpperCase() }
        val hasLowercase = password.any { it.isLowerCase() }
        val hasNumbers = password.any { it.isDigit() }
        if (!hasUppercase || !hasLowercase || !hasNumbers) {
            return PasswordStrength.WEAK
        }

        val specialChars = "!@#$%^&*()-=_+[]{}|;:,.<>/?"
        val hasSpecialChars = password.any { specialChars.contains(it) }
        if (!hasSpecialChars) {
            return PasswordStrength.MEDIUM
        }
        return PasswordStrength.STRONG
    }

    private fun updateStrengthBarColor(passwordStrength: PasswordStrength) {
        val color = when (passwordStrength) {
            PasswordStrength.WEAK -> ContextCompat.getColor(context, R.color.red)
            PasswordStrength.MEDIUM -> ContextCompat.getColor(context, R.color.yellow)
            PasswordStrength.STRONG -> ContextCompat.getColor(context, R.color.green)
        }
        progressDrawable.setTint(color)
    }

    enum class PasswordStrength {
        WEAK,
        MEDIUM,
        STRONG
    }
}
