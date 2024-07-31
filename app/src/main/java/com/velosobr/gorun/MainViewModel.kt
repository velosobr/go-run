package com.velosobr.gorun

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.velosobr.core.domain.SessionStorage
import kotlinx.coroutines.launch

class MainViewModel(
    private val sessionStorage: SessionStorage
) : ViewModel() {

    var state by mutableStateOf(MainState())
        private set

    init {
        viewModelScope.launch {
            state = state.copy(
                isCheckinAuth = true
            )
            state = state.copy(
                isLoggedIn = sessionStorage.get() != null // Check if the user is logged in or not by checking if the session storage has a value
            )

            state = state.copy(
                isCheckinAuth = false)
        }

    }
}