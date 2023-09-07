package org.nekojess.nutriease.util

import org.nekojess.nutriease.domain.dto.PatientDto

object PatientListUtil {
    fun List<PatientDto>.sortByName(): List<PatientDto>{
        return this.sortedWith(compareBy(String.CASE_INSENSITIVE_ORDER) { it.name })
    }

}