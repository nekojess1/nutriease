package org.nekojess.nutriease.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import org.nekojess.nutriease.domain.dto.PatientDto
import org.nekojess.nutriease.util.StringUtils

class PatientRepository {

    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val fireStore: FirebaseFirestore by lazy {
        Firebase.firestore
    }

    suspend fun savePatientData(patientDto: PatientDto): Result<Boolean> {
        return withContext(Dispatchers.IO) {
            try {
                val userId = auth.currentUser?.uid ?: StringUtils.EMPTY_STRING
                fireStore.collection(USER_COLLECTION)
                    .document(userId)
                    .collection(PATIENTS_COLLECTION)
                    .add(patientDto).await()
                Result.success(true)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun getPatientList(): Result<List<PatientDto>> {
        return try {
            val userId = auth.currentUser?.uid ?: StringUtils.EMPTY_STRING
            val querySnapshot = fireStore.collection(USER_COLLECTION)
                .document(userId)
                .collection(PATIENTS_COLLECTION)
                .get()
                .await()

            val patientList = mutableListOf<PatientDto>()
            for (document in querySnapshot.documents) {
                val patient = document.toObject(PatientDto::class.java)
                patient?.let {
                    patientList.add(it)
                }
            }
            Result.success(patientList)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

    companion object {
        const val USER_COLLECTION = "users"
        const val PATIENTS_COLLECTION = "patients"

    }
}
