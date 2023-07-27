package org.nekojess.nutriease.util

import android.util.Patterns
import com.google.android.material.textfield.TextInputLayout

object VerificationUtils {

    private fun String.isEmail() = !Patterns.EMAIL_ADDRESS.matcher(this).matches()

    fun TextInputLayout.isValidEmail(): Boolean{
        val email = this.editText?.text.toString().trim()
        when {
            email.isEmpty() -> this.error = "Email não pode ser vazio"
            email.isEmail() -> this.error = "Email inválido"
            else -> {
                clearError()
                return true
            }
        }
        return false
    }

    fun TextInputLayout.isEmptyText(): Boolean {
        val text = this.editText?.text.toString()
        if (text.isEmpty()) {
            this.error = "${this.hint} não pode ser vazio"
        } else {
            clearError()
            return true
        }
        return false
    }

    private fun TextInputLayout.clearError() {
        this.error = null
    }
}