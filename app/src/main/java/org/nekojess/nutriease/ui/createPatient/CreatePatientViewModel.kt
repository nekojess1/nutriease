package org.nekojess.nutriease.ui.createPatient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.nekojess.nutriease.data.PatientRepository
import org.nekojess.nutriease.domain.dto.HomeDto
import org.nekojess.nutriease.domain.dto.PatientDto

class CreatePatientViewModel(private val patientRepository: PatientRepository) : ViewModel() {

    private val fireStore: FirebaseFirestore by lazy {
        Firebase.firestore
    }

    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val _patientLiveData = MutableLiveData<HomeDto>()

    val patientLiveData: LiveData<HomeDto>
        get() = _patientLiveData


    private fun savePatientData() {
        val user = auth.currentUser
        val userId = user?.uid

        if (userId != null) {
            val patientDto = PatientDto(
                name = "Geovana"
            )
            fireStore.collection("users")
                .document(userId)
                .collection("patients")
                .add(patientDto)
                .addOnSuccessListener {

                }
                .addOnFailureListener { _ ->

                }
        }
    }
}