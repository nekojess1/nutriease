package org.nekojess.nutriease.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.nekojess.nutriease.domain.dto.HomeDto
import org.nekojess.nutriease.domain.dto.PatientDto
import org.nekojess.nutriease.domain.dto.UserDto

class HomeViewModel : ViewModel() {

    private val fireStore: FirebaseFirestore by lazy {
        Firebase.firestore
    }

    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val _userLiveData = MutableLiveData<HomeDto>()

    val userLiveData: LiveData<HomeDto>
        get() = _userLiveData

    fun getUserData() {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            fireStore.collection("users").document(userId).get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        val user = document.toObject(UserDto::class.java)
                        getPatients(userId, user)
                    }
                }
                .addOnFailureListener {

                }
        }
    }

    private fun getPatients(
        userId: String,
        user: UserDto?
    ) {
        fireStore.collection("users").document(userId).collection("patients").get()
            .addOnSuccessListener { querySnapshot ->
                val patientList = mutableListOf<PatientDto>()
                for (document in querySnapshot.documents) {
                    val patient = document.toObject(PatientDto::class.java)
                    patient?.let {
                        patientList.add(it)
                    }
                }
                _userLiveData.value = HomeDto(
                    user,
                    patientList
                )
            }
            .addOnFailureListener {

            }
    }

    fun signOutUser(){
        auth.signOut()
    }
}
