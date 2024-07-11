package com.velosobr.core.domain

import java.sql.RowId

data class AuthInfo(
    val accessToken: String,
    val refreshToken: String,
    val userId: String
)
