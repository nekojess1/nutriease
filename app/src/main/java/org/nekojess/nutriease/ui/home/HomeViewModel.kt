package org.nekojess.nutriease.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import org.nekojess.nutriease.data.PatientRepository
import org.nekojess.nutriease.data.UserRepository
import org.nekojess.nutriease.domain.dto.HomeDto
import org.nekojess.nutriease.domain.dto.UserDto

class HomeViewModel(
    private val patientRepository: PatientRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val _userLiveData = MutableLiveData<HomeDto>()

    val userLiveData: LiveData<HomeDto>
        get() = _userLiveData

    fun getUserData() {
        viewModelScope.launch {
            val result = userRepository.getUserData()
            result.onSuccess { userDto ->
                getPatientList(userDto)
            }
            result.onFailure { exception ->

            }
        }
    }

    private fun getPatientList(user: UserDto) {
        viewModelScope.launch {
            val result = patientRepository.getPatientList()
            result.onSuccess { patientList ->
                _userLiveData.value = HomeDto(
                    user,
                    patientList
                )
            }
            result.onFailure { exception ->

            }
         }
    }

    fun signOutUser() {
        auth.signOut()
    }
}
