package com.samwrotethecode.socialease.ui.presentation.start.models

import android.net.Uri


data class GoogleSignInResult(
    val data: UserData?,
    val errorMessage: String?,
)

data class UserData(
    val userID: String,
    val displayName: String?,
    val photoURL: Uri?,
    val userEmail: String?,
)