package org.nekojess.nutriease.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import org.nekojess.nutriease.domain.dto.UserDto
import org.nekojess.nutriease.util.StringUtils.EMPTY_STRING

class UserRepository {

    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val fireStore: FirebaseFirestore by lazy {
        Firebase.firestore
    }

    private val userId = auth.currentUser?.uid ?: EMPTY_STRING

    suspend fun getUserData(): Result<UserDto> = withContext(Dispatchers.IO) {
        try {
            val document = fireStore.collection(USER_COLLECTION)
                .document(userId)
                .get()
                .await()

            if (document != null && document.exists()) {
                Result.success(document.toObject(UserDto::class.java)!!)
            } else {
                Result.failure(Exception("User not found"))
            }
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

    companion object {
        const val USER_COLLECTION = "users"
    }
}
