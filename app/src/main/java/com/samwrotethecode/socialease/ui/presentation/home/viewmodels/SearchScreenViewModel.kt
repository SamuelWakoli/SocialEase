package com.samwrotethecode.socialease.ui.presentation.home.viewmodels

import android.content.Context
import android.util.Log
import androidx.core.content.ContextCompat.getString
import androidx.lifecycle.ViewModel
import com.samwrotethecode.socialease.data.local_data.SubTopicsModel
import com.samwrotethecode.socialease.data.local_data.allSubTopics
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class SearchScreenUiState(
    val query: String = "",
    val isSearching: Boolean = false,
    val searchResults: MutableList<SubTopicsModel> = mutableListOf(),
)

class SearchScreenViewModel : ViewModel() {
    private var _uiState: MutableStateFlow<SearchScreenUiState> =
        MutableStateFlow(SearchScreenUiState())
    val uiState: StateFlow<SearchScreenUiState> = _uiState.asStateFlow()

    val data = allSubTopics

    init {
        _uiState.update {
            it.copy(
                query = "",
                isSearching = false,
            )
        }
    }

    fun updateSearchQuery(query: String) {
        _uiState.update {
            it.copy(query = query)
        }
    }

    fun startSearch(context: Context) {
        val searchResults: MutableList<SubTopicsModel> = mutableListOf()
        Log.d("SEARCH SCREEN", "interactive")
        Log.d("SEARCH SCREEN", "data size ${data.size}")
        Log.d("SEARCH SCREEN", "first title ${getString(context, data.first().titleId)}")
        for (item in data) {
            val currentTitle = getString(context, item.titleId)

            if (currentTitle.contains(
                    uiState.value.query,
                    ignoreCase = true,
                )
            ) {
                searchResults.add(item)
                Log.d("SEARCH SCREEN", "ITEM: ${getString(context, item.titleId)}")
            }
        }

        _uiState.update {
            it.copy(searchResults = searchResults)
        }
        Log.d("SEARCH SCREEN", "search ended")
    }
}