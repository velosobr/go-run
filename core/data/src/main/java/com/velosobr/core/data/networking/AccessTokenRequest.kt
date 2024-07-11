package com.velosobr.core.data.networking

data class AccessTokenRequest(
    val refreshToken: String,
    val expirationTimestamp: String
) {
}