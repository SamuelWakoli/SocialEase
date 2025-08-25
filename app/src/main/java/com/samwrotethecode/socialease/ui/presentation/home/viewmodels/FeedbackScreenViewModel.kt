package com.samwrotethecode.socialease.ui.presentation.home.viewmodels

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.Firebase
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

class FeedbackScreenViewModel : ViewModel() {
    private var _uiState: MutableStateFlow<FeedbackScreenState> =
        MutableStateFlow(FeedbackScreenState())
    val uiState = _uiState.asStateFlow()

    private val database = Firebase.firestore
    private val userId = Firebase.auth.currentUser?.email ?: "anonymous"
    private val feedbacksCollection = "feedbacks"


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
        if (uiState.value.text == "") {
            _uiState.update { it.copy(showError = !it.showError) }
        } else {
            _uiState.update {
                it.copy(
                    isSendingFeedback = true
                )
            }

            val calender = Calendar.getInstance()
            val time = "${calender.time}"

            val data = hashMapOf(
                "text" to uiState.value.text,
                "time" to time,
                "senderId" to userId,
                "isRead" to false,
            )

            database.collection(feedbacksCollection).document(time).set(data)
                .addOnSuccessListener {
                    _uiState.update {
                        it.copy(
                            isSendingFeedback = false,
                            showError = false,
                            feedbackSent = true,
                            errorMessage = "",
                            text = "",
                        )
                    }
                }.addOnFailureListener { error ->
                    _uiState.update {
                        it.copy(
                            isSendingFeedback = false,
                            showError = false,
                            feedbackSent = false,
                            errorMessage = error.message.toString(),
                        )
                    }
                }
        }
    }

}