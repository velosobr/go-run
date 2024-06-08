package com.velosobr.core.presentation.ui

import com.velosobr.core.domain.util.DataError

fun DataError.asUiText() :UiText {
    return when (this) {
        is DataError.NetworkError -> when (this) {
            DataError.NetworkError.NO_INTERNET_CONNECTION -> UiText.StringResource(R.string.no_internet_connection)
            DataError.NetworkError.NO_RESPONSE -> UiText.StringResource(R.string.no_response)
            DataError.NetworkError.REQUEST_TIMEOUT -> UiText.StringResource(R.string.request_timeout)
            DataError.NetworkError.CONFILCT -> UiText.StringResource(R.string.conflict)
            DataError.NetworkError.TOO_MANY_REQUESTS -> UiText.StringResource(R.string.too_many_requests)
            DataError.NetworkError.PAYLOAD_TOO_LARGE -> UiText.StringResource(R.string.payload_too_large)
            DataError.NetworkError.SERVER_ERROR -> UiText.StringResource(R.string.server_error)
            DataError.NetworkError.SERIALIZATION -> UiText.StringResource(R.string.serialization)
            DataError.NetworkError.UNAUTHORIZED -> UiText.StringResource(R.string.unauthorized)
            DataError.NetworkError.UNKNOWN -> UiText.StringResource(R.string.unknown)
        }
        is DataError.LocalError -> when (this) {
            DataError.LocalError.DATABASE_ERROR -> UiText.StringResource(R.string.database_error)
            DataError.LocalError.DISK_FULL -> UiText.StringResource(R.string.disk_full)
            DataError.LocalError.NO_DATA -> UiText.StringResource(R.string.no_data)
            DataError.LocalError.NO_PERMISSION -> UiText.StringResource(R.string.no_permission)
            DataError.LocalError.UNKNOWN -> UiText.StringResource(R.string.unknown)
        }
    }
}