package com.velosobr.core.domain.util

sealed interface DataError: Error {

    enum class NetworkError: DataError {
        NO_INTERNET_CONNECTION,
        NO_RESPONSE,
        REQUEST_TIMEOUT,
        CONFLICT,
        TOO_MANY_REQUESTS,
        PAYLOAD_TOO_LARGE,
        SERVER_ERROR,
        SERIALIZATION,
        UNAUTHORIZED,
        FORBIDDEN,
        NOT_IMPLEMENTED,
        BAD_GATEWAY,
        SERVICE_UNAVAILABLE,
        GATEWAY_TIMEOUT,
        UNKNOWN
    }

    enum class LocalError: DataError {
        DATABASE_ERROR,
        DISK_FULL,
        NO_DATA,
        NO_PERMISSION,
        UNKNOWN
    }
}