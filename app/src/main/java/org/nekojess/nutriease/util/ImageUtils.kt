package org.nekojess.nutriease.util

import java.util.UUID

object ImageUtils {

    fun generateUniqueImageName(): String {
        return UUID.randomUUID().toString()
    }
}
