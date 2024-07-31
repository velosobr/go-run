package com.velosobr.auth.data

import com.velosobr.auth.domain.AuthRepository
import com.velosobr.core.data.networking.post
import com.velosobr.core.domain.AuthInfo
import com.velosobr.core.domain.SessionStorage
import com.velosobr.core.domain.util.DataError
import com.velosobr.core.domain.util.EmptyResult
import com.velosobr.core.domain.util.Result
import com.velosobr.core.domain.util.asEmptyDataResult
import io.ktor.client.HttpClient


class AuthRepositoryImpl(
    private val httpClient: HttpClient,
    private val session: SessionStorage
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
        val result = httpClient.post<LoginRequest, LoginResponse>(
            route = "/login",
            body = LoginRequest(email, password)
        )

        if (result is Result.Success) {
            session.set(
                AuthInfo(
                    accessToken = result.data.accessToken,
                    refreshToken = result.data.refreshToken,
                    userId = result.data.userId
                )
            )
        }

        return result.asEmptyDataResult()
    }
}