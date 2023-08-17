package org.nekojess.nutriease.data.repository.user

import org.nekojess.nutriease.domain.dto.LoginDto
import org.nekojess.nutriease.domain.dto.UserDto

interface UserRepository {
    suspend fun getUserData(): Result<UserDto>
    suspend fun authUser(userLogin: LoginDto): Result<Boolean>
    suspend fun createUser(userLogin: LoginDto): Result<Boolean>
    suspend fun saveUserData(userData: UserDto): Result<Boolean>
}
