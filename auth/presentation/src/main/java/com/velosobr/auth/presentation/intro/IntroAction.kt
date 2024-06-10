package com.velosobr.auth.presentation.intro

sealed interface IntroAction {
    data object OnSignInClick : IntroAction

    data object OnSIgnUpCLick : IntroAction
}