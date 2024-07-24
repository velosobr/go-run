package com.velosobr.auth.data

import com.velosobr.auth.domain.AuthRepository
import com.velosobr.core.data.networking.post
import com.velosobr.core.domain.util.DataError
import com.velosobr.core.domain.util.EmptyResult
import io.ktor.client.HttpClient


class AuthRepositoryImpl(
    private val httpClient: HttpClient
) : AuthRepository {
    override suspend fun register(
        email: String, password: String
    ): EmptyResult<DataError.NetworkError> {
        return httpClient.post(
            route = "/register",
            body = RegisterRequest(email, password)
        )
    }

    override suspend fun login(
        email: String,
        password: String
    ): EmptyResult<DataError.NetworkError> {
        TODO("Not yet implemented")
    }
}