package com.samwrotethecode.socialease.ui.presentation.home.viewmodels

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Calendar

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

    val database = Firebase.firestore
    val userId = Firebase.auth.currentUser!!.email

    companion object {
        const val FEEDBACKS_COLLECTION = "feedbacks"
    }

    init {
        resetState()
    }

    fun resetState() {
        _uiState.update {
            it.copy(
                text = "",
                showError = false,
                isSendingFeedback = false,
                feedbackSent = false,
                errorMessage = "",
            )
        }
    }

    fun updateFeedbackText(text: String) {
        _uiState.update {
            it.copy(text = text, showError = false)
        }
    }
    
    fun sendFeedback() {
        // TODO:  
    }

}