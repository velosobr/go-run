package com.velosobr.run.presentation.active_run

import com.velosobr.core.domain.location.Location
import kotlin.time.Duration

data class ActiveRunState(
    val elapsedTime: Duration = Duration.ZERO,
    val shouldTrack: Boolean = false,
    val hasStartedRunning: Boolean = false,
    val currentLocation: Location
)