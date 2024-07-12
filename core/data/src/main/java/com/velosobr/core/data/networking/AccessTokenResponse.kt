package com.velosobr.core.data.networking

data class AccessTokenResponse(
    val accessToken: String,
    val refreshToken: String,
    val expirationTimeStamp: Long
)
