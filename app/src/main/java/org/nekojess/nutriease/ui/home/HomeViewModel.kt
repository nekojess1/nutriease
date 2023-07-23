package org.nekojess.nutriease.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.nekojess.nutriease.domain.dto.UserDto

class HomeViewModel : ViewModel() {

    private val fireStore: FirebaseFirestore by lazy {
        Firebase.firestore
    }

    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val _userLiveData = MutableLiveData<UserDto>()

    val userLiveData: LiveData<UserDto>
        get() = _userLiveData

    fun getUserData() {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            fireStore.collection("users").document(userId).get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        _userLiveData.value = document.toObject(UserDto::class.java)
                    }
                }
                .addOnFailureListener { exception: Exception? ->

                }
        }
    }
}
