package com.samwrotethecode.socialease.ui.presentation.start.models

data class SignInUiModel(
    // userData
    val name: String = "",
    val email: String = "",
    val password: String = "",

    // observed at launchedEffect
    val isSignInSuccess: Boolean = false,
    val errorMessage: String? = null,

    val isSignInButtonLoading: Boolean = false,
    val showPassword: Boolean = false,
    val showNameError: Boolean = false,
    val showEmailError: Boolean = false,
    val showPasswordError: Boolean = false,

    val showDialogPwdResetEmailSent: Boolean = false
)
