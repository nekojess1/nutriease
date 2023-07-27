package org.nekojess.nutriease.util

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

object StringUtils {

    const val EMPTY_STRING = ""
    fun String.hashPassword(): String {
        try {
            val messageDigest = MessageDigest.getInstance("SHA-256")
            val passwordBytes = this.toByteArray()
            val hashedBytes = messageDigest.digest(passwordBytes)
            val hashedPassword = StringBuilder()
            for (byte in hashedBytes) {
                hashedPassword.append(String.format("%02x", byte))
            }

            return hashedPassword.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        return this
    }
}
