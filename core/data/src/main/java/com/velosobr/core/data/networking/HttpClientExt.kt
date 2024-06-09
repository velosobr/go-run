package com.velosobr.core.data.networking

import com.velosobr.core.data.BuildConfig

fun constructRoute(route: String): String {
    return when {
        route.contains(BuildConfig.BASE_URL) -> route
        route.startsWith("/") -> "${BuildConfig.BASE_URL}$route"
        else -> "${BuildConfig.BASE_URL}/$route"
    }
}
