package com.velosobr.core.domain.location

data class Location(
    val latitude: Double,
    val longitude: Double,
    val altitude: Double? = null,
    val speed: Float? = null,
    val accuracy: Float? = null,
    val bearing: Float? = null,
    val timestamp: Long? = null
)
