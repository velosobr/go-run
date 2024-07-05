package com.velosobr.auth.domain

import com.velosobr.core.domain.util.DataError
import com.velosobr.core.domain.util.EmptyResult

interface AuthRepository {

    suspend fun register(email: String, password: String): EmptyResult<DataError.NetworkError>
}