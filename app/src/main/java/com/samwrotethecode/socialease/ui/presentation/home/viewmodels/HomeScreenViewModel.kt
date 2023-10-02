package com.samwrotethecode.socialease.ui.presentation.home.viewmodels

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class HomeUiStateModel(
    val photoUrl: Uri? = null,
    val displayName: String? = null,
    val email: String? = null,
    val showDropdownMenu: Boolean = false
)

class HomeScreenViewModel : ViewModel() {
    private var _uiState: MutableStateFlow<HomeUiStateModel> = MutableStateFlow(HomeUiStateModel())
    val uiState: StateFlow<HomeUiStateModel> = _uiState.asStateFlow()

    val currentUser: FirebaseUser? = Firebase.auth.currentUser

    init {
        resetHomeState()
        getUserData()
    }

    private fun getUserData() {
        _uiState.update {
            it.copy(
                photoUrl = currentUser?.photoUrl,
                displayName = currentUser?.displayName,
                email = currentUser?.email,
            )
        }
    }

    private fun resetHomeState() {
        _uiState.update {
            it.copy(
                showDropdownMenu = false,
            )
        }
    }

    fun updateAppbarDropDownMenu() {
        _uiState.update {
            it.copy(
                showDropdownMenu = !it.showDropdownMenu,
            )
        }
    }
}