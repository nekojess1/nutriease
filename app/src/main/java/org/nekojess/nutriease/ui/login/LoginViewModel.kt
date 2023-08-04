package org.nekojess.nutriease.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.nekojess.nutriease.data.UserRepository
import org.nekojess.nutriease.domain.dto.LoginDto

class LoginViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _loginResultLiveData = MutableLiveData<Boolean>()
    val loginResultLiveData: LiveData<Boolean> get() = _loginResultLiveData

    fun authUser(userLogin: LoginDto) {
        viewModelScope.launch {
            val loginResult = userRepository.authUser(userLogin)
            loginResult.onSuccess { isAuth ->
                _loginResultLiveData.value = isAuth
            }
            loginResult.onFailure { exception ->

            }
        }
    }
}
