package com.velosobr.core.domain.util

sealed interface DataError: Error {

    enum class NetworkError: DataError {
        NO_INTERNET_CONNECTION,
        NO_RESPONSE,
        REQUEST_TIMEOUT,
        CONFILCT,
        TOO_MANY_REQUESTS,
        PAYLOAD_TOO_LARGE,
        SERVER_ERROR,
        SERIALIZATION,
        UNAUTHORIZED,
        UNKNOWN
    }

    enum class LocalError: DataError {
        DATABASE_ERROR,
        DISK_FULL,
        NO_DATA,
        UNKNOWN
    }
}