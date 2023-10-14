package com.samwrotethecode.socialease.ui.presentation.home.viewmodels

import androidx.lifecycle.ViewModel
import com.samwrotethecode.socialease.data.local_data.SubTopicsModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class BookmarksScreenStateModel(
    val topicsList: List<SubTopicsModel>? = null,
    // ReadingScreen state
    val currentSubTopic: SubTopicsModel? = null,
)

class BookmarksScreenViewModel : ViewModel() {
    private var _uiState: MutableStateFlow<BookmarksScreenStateModel> =
        MutableStateFlow(BookmarksScreenStateModel())
    val uiState: StateFlow<BookmarksScreenStateModel> = _uiState.asStateFlow()

    fun updateReadingScreenState(currentSubTopic: SubTopicsModel) {
        _uiState.update { it.copy(currentSubTopic = currentSubTopic) }
    }

    fun removeBookmark() {

    }


}