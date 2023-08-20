package org.nekojess.nutriease.data.repository.patient

import android.net.Uri
import androidx.core.net.toUri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import org.nekojess.nutriease.domain.dto.PatientDto
import org.nekojess.nutriease.util.ImageUtils.generateUniqueImageName
import org.nekojess.nutriease.util.StringUtils

class PatientRepositoryImpl : PatientRepository {

    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val fireStore: FirebaseFirestore by lazy {
        Firebase.firestore
    }

    private val storage: FirebaseStorage by lazy {
        FirebaseStorage.getInstance()
    }

    override suspend fun savePatientData(patientDto: PatientDto): Result<Boolean> {
        return withContext(Dispatchers.IO) {
            try {
                val userId = auth.currentUser?.uid ?: StringUtils.EMPTY_STRING
                if (patientDto.userImage.isNotEmpty()) {
                    val imageDownloadUrl = uploadImageAndGetUrl(patientDto.userImage.toUri())
                    patientDto.userImage = imageDownloadUrl
                }
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

    private suspend fun uploadImageAndGetUrl(imageUri: Uri): String {
        val imageName = generateUniqueImageName()
        val storageRef = storage.reference.child("patients/$imageName.jpg")

        val uploadTask = storageRef.putFile(imageUri)
        val imageDownloadUrl = uploadTask.continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let { throw it }
            }
            storageRef.downloadUrl
        }.await()

        return imageDownloadUrl.toString()
    }

    override suspend fun getPatientList(): Result<List<PatientDto>> {
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
