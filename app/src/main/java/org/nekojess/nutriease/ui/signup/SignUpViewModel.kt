package org.nekojess.nutriease.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.nekojess.nutriease.data.repository.UserRepository
import org.nekojess.nutriease.domain.dto.LoginDto
import org.nekojess.nutriease.domain.dto.UserDto

class SignUpViewModel(private val patientRepository: UserRepository) : ViewModel() {

    private val _userLiveData = MutableLiveData<Boolean>()

    val userLiveData: LiveData<Boolean>
        get() = _userLiveData

    fun saveUser(loginDto: LoginDto, userData: UserDto) {
        viewModelScope.launch {
            val result = patientRepository.createUser(loginDto)
            result.onSuccess {
                saveUserData(userData)
            }
            result.onFailure { exception ->

            }
        }
    }

    private fun saveUserData(userData: UserDto) {
        viewModelScope.launch {
            val result = patientRepository.saveUserData(userData)
            result.onSuccess { successRegister ->
                _userLiveData.value = successRegister
            }
            result.onFailure { exception ->

            }
        }
    }
}
