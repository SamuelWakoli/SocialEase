package com.samwrotethecode.socialease.ui.presentation.home.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class FeedbackScreenState(
    val text: String = "",
    val showError: Boolean = false,
    val isSendingFeedback: Boolean = false,
    val feedbackSent: Boolean = false,
    val errorMessage: String = "",
)

class FeedbackScreenViewmodel : ViewModel() {
    private var _uiState: MutableStateFlow<FeedbackScreenState> =
        MutableStateFlow(FeedbackScreenState())
    val uiState = _uiState.asStateFlow()

    fun updateFeedbackText(text: String) {
        _uiState.update {
            it.copy(text = text, showError = false)
        }
    }
    
    fun sendFeedback() {
        // TODO:  
    }

}