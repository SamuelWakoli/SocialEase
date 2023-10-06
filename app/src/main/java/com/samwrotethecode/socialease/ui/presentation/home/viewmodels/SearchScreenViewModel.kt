package com.samwrotethecode.socialease.ui.presentation.home.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class SearchScreenUiState(
    val query: String = "",
    val noResults: Boolean = true,
    val isSearching: Boolean = false,
//    val searchResults: // list data type
)

class SearchScreenViewModel : ViewModel() {
    private var _uiState: MutableStateFlow<SearchScreenUiState> =
        MutableStateFlow(SearchScreenUiState())
    val uiState: StateFlow<SearchScreenUiState> = _uiState.asStateFlow()

    init {
        _uiState.update {
            it.copy(
                query = "",
                noResults = true,
                isSearching = false,
            )
        }
    }

    fun updateSearchQuery(query: String) {
        _uiState.update {
            it.copy(query = query)
        }
    }

    fun startSearch() {

    }
}