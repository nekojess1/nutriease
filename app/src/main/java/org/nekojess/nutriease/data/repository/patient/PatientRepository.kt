package org.nekojess.nutriease.data.repository.patient

import org.nekojess.nutriease.domain.dto.PatientDto

interface PatientRepository {
    suspend fun getPatientList(): Result<List<PatientDto>>
    suspend fun savePatientData(patientDto: PatientDto): Result<Boolean>
}
