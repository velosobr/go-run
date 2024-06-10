package com.velosobr.core.data.networking

import com.velosobr.core.data.BuildConfig
import com.velosobr.core.domain.util.DataError
import com.velosobr.core.domain.util.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.cio.Response
import kotlinx.coroutines.CancellationException
import kotlinx.serialization.SerializationException
import java.nio.channels.UnresolvedAddressException

suspend inline fun <reified Response : Any> HttpClient.get(
    route: String, queryParameters: Map<String, Any?> = mapOf()
): Result<Response, DataError.NetworkError> {
    return safeCall {
        get {
            url(constructRoute(route))
            queryParameters.forEach { (key, value) ->
                parameter(key, value)
            }
        }
    }
}
suspend inline fun <reified Response : Any> HttpClient.delete(
    route: String, queryParameters: Map<String, Any?> = mapOf()
): Result<Response, DataError.NetworkError> {
    return safeCall {
        delete {
            url(constructRoute(route))
            queryParameters.forEach { (key, value) ->
                parameter(key, value)
            }
        }
    }
}
suspend inline fun <reified Request : Any> HttpClient.post(
    route: String, body: Request
): Result<Response, DataError.NetworkError> {
    return safeCall {
        post {
            url(constructRoute(route))
            setBody(body)
        }
    }
}

suspend inline fun <reified T> safeCall(execute: () -> HttpResponse): Result<T, DataError.NetworkError> {
    val response = try {
        execute()

    } catch (e: UnresolvedAddressException) {
        e.printStackTrace()
        return Result.Error(DataError.NetworkError.NO_INTERNET_CONNECTION)
    } catch (e: SerializationException) {
        e.printStackTrace()
        return Result.Error(DataError.NetworkError.SERIALIZATION)
    } catch (e: Exception) {
        if (e is CancellationException) throw e
        e.printStackTrace()
        return Result.Error(DataError.NetworkError.UNKNOWN)
    }

    return responseToResult(response)
}

suspend inline fun <reified T> responseToResult(response: HttpResponse): Result<T, DataError.NetworkError> {
    return when (response.status.value) {
        in 200..299 -> Result.Success(response.body<T>())
        400 -> Result.Error(DataError.NetworkError.CONFLICT)
        401 -> Result.Error(DataError.NetworkError.UNAUTHORIZED)
        403 -> Result.Error(DataError.NetworkError.FORBIDDEN)
        404 -> Result.Error(DataError.NetworkError.NO_RESPONSE)
        408 -> Result.Error(DataError.NetworkError.REQUEST_TIMEOUT)
        409 -> Result.Error(DataError.NetworkError.CONFLICT)
        413 -> Result.Error(DataError.NetworkError.PAYLOAD_TOO_LARGE)
        429 -> Result.Error(DataError.NetworkError.TOO_MANY_REQUESTS)
        500 -> Result.Error(DataError.NetworkError.SERVER_ERROR)
        501 -> Result.Error(DataError.NetworkError.NOT_IMPLEMENTED)
        502 -> Result.Error(DataError.NetworkError.BAD_GATEWAY)
        503 -> Result.Error(DataError.NetworkError.SERVICE_UNAVAILABLE)
        504 -> Result.Error(DataError.NetworkError.GATEWAY_TIMEOUT)
        in 505..599 -> Result.Error(DataError.NetworkError.SERVER_ERROR)
        else -> Result.Error(DataError.NetworkError.UNKNOWN)
    }

}

fun constructRoute(route: String): String {
    return when {
        route.contains(BuildConfig.BASE_URL) -> route
        route.startsWith("/") -> "${BuildConfig.BASE_URL}$route"
        else -> "${BuildConfig.BASE_URL}/$route"
    }
}
