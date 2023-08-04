package org.nekojess.nutriease.ui.createPatient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.nekojess.nutriease.data.repository.PatientRepository
import org.nekojess.nutriease.domain.dto.PatientDto

class CreatePatientViewModel(private val patientRepository: PatientRepository) : ViewModel() {

    private val _patientLiveData = MutableLiveData<Boolean>()

    val patientLiveData: LiveData<Boolean>
        get() = _patientLiveData

    fun savePatientData(patientDto: PatientDto) {
        viewModelScope.launch {
            val result = patientRepository.savePatientData(patientDto)
            result.onSuccess { successRegister ->
                _patientLiveData.value = successRegister
            }
            result.onFailure { exception ->

            }
        }
    }
}
