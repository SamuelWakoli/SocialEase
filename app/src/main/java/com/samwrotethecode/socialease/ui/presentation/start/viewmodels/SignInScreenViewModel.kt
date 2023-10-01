package com.samwrotethecode.socialease.ui.presentation.start.viewmodels

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import com.samwrotethecode.socialease.ui.presentation.start.models.GoogleSignInResult
import com.samwrotethecode.socialease.ui.presentation.start.models.SignInUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignInScreenViewModel : ViewModel() {
    private var _uiState: MutableStateFlow<SignInUiModel> = MutableStateFlow(SignInUiModel())
    val uiState: StateFlow<SignInUiModel> = _uiState.asStateFlow()

    init {
        resetData()
    }


    private fun resetData() {
        // clear userData
        _uiState.update {
            it.copy(
                name = "",
                email = "",
                password = "",
            )
        }
    }

    fun onSignInResult(result: GoogleSignInResult) {
        _uiState.update {
            it.copy(
                isSignInSuccess = result.data != null,
                errorMessage = result.errorMessage,
            )
        }
    }

    fun updateName(value: String) {

        _uiState.update {
            it.copy(
                name = value,
                showNameError = false,
                errorMessage = null,
            )
        }
    }


    fun updateEmail(value: String) {
        _uiState.update {
            it.copy(
                email = value,
                showEmailError = false,
                errorMessage = null,
            )
        }
    }

    fun updatePassword(value: String) {

        _uiState.update {
            it.copy(
                password = value,
                showPasswordError = false,
                errorMessage = null
            )
        }
    }

    fun hideOrShowPassword() {
        _uiState.update { it.copy(showPassword = !(it.showPassword)) }
    }

    fun signInWithEmailPwd() {
        val email = uiState.value.email
        val password = uiState.value.password
        if (email.isEmpty()) {
            _uiState.update { it.copy(showEmailError = true) }
        } else if (password.isEmpty()) {
            _uiState.update { it.copy(showPasswordError = true) }
        } else {
            _uiState.update {
                it.copy(
                    isSignInButtonLoading = true
                )
            }

            Firebase.auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { authResultTask ->
                    if (authResultTask.isSuccessful) {
                        _uiState.update {
                            it.copy(
                                isSignInSuccess = true,
                            )
                        }
                    }
                    if (authResultTask.isCanceled) {
                        _uiState.update {
                            it.copy(
                                isSignInButtonLoading = false,
                                errorMessage = "Sign in canceled"
                            )
                        }
                    }
                }.addOnFailureListener { exception ->
                    _uiState.update {
                        it.copy(
                            isSignInButtonLoading = false,
                            errorMessage = exception.message
                        )
                    }
                }
        }
    }

    fun registerWithEmailPwd() {
        val name = uiState.value.name
        val email = uiState.value.email
        val password = uiState.value.password

        if (name.isEmpty()) {
            _uiState.update { it.copy(showNameError = true) }
        } else if (email.isEmpty()) {
            _uiState.update { it.copy(showEmailError = true) }
        } else if (password.isEmpty()) {
            _uiState.update { it.copy(showPasswordError = true) }
        } else {
            _uiState.update {
                it.copy(
                    isSignInButtonLoading = true
                )
            }

            Firebase.auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { authResultTask ->
                    if (authResultTask.isSuccessful) {
                        // since user registration is successful, update display name
                        val profileUpdate = userProfileChangeRequest {
                            displayName = name
                        }
                        Firebase.auth.currentUser?.updateProfile(profileUpdate)

                        _uiState.update {
                            it.copy(
                                isSignInSuccess = true,
                            )
                        }
                    }
                    if (authResultTask.isCanceled) {
                        _uiState.update {
                            it.copy(
                                isSignInButtonLoading = false,
                                errorMessage = "Registration canceled",
                            )
                        }
                    }
                }.addOnFailureListener { exception ->
                    _uiState.update {
                        it.copy(
                            isSignInButtonLoading = false,
                            errorMessage = exception.message
                        )
                    }
                }

        }
    }

    fun sendPwdResetLink() {
        val email = uiState.value.email

    }
}