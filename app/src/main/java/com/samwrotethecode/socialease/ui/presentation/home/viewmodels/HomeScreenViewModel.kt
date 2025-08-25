package com.samwrotethecode.socialease.ui.presentation.home.viewmodels

import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.firestore
import com.google.firebase.Firebase
import com.samwrotethecode.socialease.R
import com.samwrotethecode.socialease.data.local_data.SubTopicsModel
import com.samwrotethecode.socialease.data.local_data.allSubTopics
import com.samwrotethecode.socialease.data.local_data.assertivenessSubTopics
import com.samwrotethecode.socialease.data.local_data.communicationSubTopics
import com.samwrotethecode.socialease.data.local_data.conflictResolutionSubTopics
import com.samwrotethecode.socialease.data.local_data.cooperationSubTopics
import com.samwrotethecode.socialease.data.local_data.empathySubTopics
import com.samwrotethecode.socialease.data.local_data.problemSolvingSubTopics
import com.samwrotethecode.socialease.data.local_data.relationshipBuildingSubTopics
import com.samwrotethecode.socialease.data.local_data.selfAwarenessSubTopics
import com.samwrotethecode.socialease.data.local_data.selfManagementSubTopics
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class HomeUiStateModel(
    val photoUrl: Uri? = null,
    val displayName: String? = null,
    val email: String? = null,
    val showDropdownMenu: Boolean = false,
    @param:DrawableRes val currentBackgroundImage: Int? = null,

    // SubTopicsScreen state
    val currentCategory: TopicCategories? = null,
    @param:StringRes val currentSubTopicTitleId: Int? = null,
    val currentSubTopicsList: List<SubTopicsModel>? = null,

    // ReadingScreen state
    val currentSubTopic: SubTopicsModel? = null,

    // Bookmarking state
    var bookmarksIds: MutableList<Int>? = mutableListOf(),
    var bookmarks: MutableList<SubTopicsModel> = mutableListOf(),
    val updatingBookmarkStatusError: String = "",
)

enum class TopicCategories {
    COMMUNICATION, RELATIONSHIP_BUILDING, COOPERATION, CONFLICT_RESOLUTION, PROBLEM_SOLVING, SELF_AWARENESS, SELF_MANAGEMENT, EMPATHY, ASSERTIVENESS
}

class HomeScreenViewModel : ViewModel() {
    private var _uiState: MutableStateFlow<HomeUiStateModel> = MutableStateFlow(HomeUiStateModel())
    val uiState: StateFlow<HomeUiStateModel> = _uiState.asStateFlow()

    private val auth: FirebaseAuth = Firebase.auth
    private var currentUser: FirebaseUser? = auth.currentUser


    private val usersCollection = "users"
    private val userBookmarksField = "bookmarks"


    private val database = Firebase.firestore
    private val userDataReference =
        database.collection(usersCollection).document(currentUser?.email ?: "anonymous")

    init {
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
        currentUser?.email?.let {
            userDataReference.get().addOnSuccessListener { documentSnapshot ->
                // update bookmark ids
                val bookmarksIdsFromDB =
                    (documentSnapshot.data?.get(userBookmarksField)) as? MutableList<Int>
                        ?: mutableListOf()
                _uiState.update {
                    it.copy(
                        bookmarksIds = bookmarksIdsFromDB,
                        bookmarks =
                            allSubTopics.filter { subtopic ->
                                bookmarksIdsFromDB.any { id -> id == subtopic.titleId }
                            }.toMutableList()
                    )
                }
            }
        }
    }

    fun updateBookmark(subtopic: SubTopicsModel, isBookmarked: Boolean) {
        val bookmarksIds = mutableListOf<Int>()

        /**
         * using bookmarksIds directly from the ui state doesn't update on being reassigned
         */

        val bookmarks = uiState.value.bookmarks
        if (currentUser?.email != null) {

            if (!isBookmarked) { //not bookmarked because it is updated on click
                bookmarks.remove(subtopic)
            } else {
                bookmarks.add(subtopic)
            }

            bookmarks.forEach { bookmarksIds.add(it.titleId) }
            _uiState.update { it.copy(bookmarks = bookmarks, bookmarksIds = bookmarksIds) }

            val data = mapOf<String, List<Int>>(
                userBookmarksField to bookmarksIds
            )
            userDataReference.set(data, SetOptions.merge()).addOnFailureListener { e ->
                _uiState.update {
                    it.copy(
                        updatingBookmarkStatusError = e.message.toString(),
                    )
                }
            }
        }
    }

    fun updateAppbarDropDownMenu() {
        _uiState.update {
            it.copy(
                showDropdownMenu = !it.showDropdownMenu,
            )
        }
    }

    fun updateTopicCategory(currentCategory: TopicCategories) {
        _uiState.update { it.copy(currentCategory = currentCategory) }

        when (uiState.value.currentCategory) {
            TopicCategories.COMMUNICATION -> {
                _uiState.update {
                    it.copy(
                        currentSubTopicTitleId = R.string.communication,
                        currentSubTopicsList = communicationSubTopics,
                        currentBackgroundImage = R.drawable.communication,
                    )
                }
//                Log.d(
//                    "CURRENT SUB TOPIC TITLE ID",
//                    "ASSIGNED: ${_uiState.value.currentSubTopicTitleId} "
//                )
            }

            TopicCategories.RELATIONSHIP_BUILDING -> {
                _uiState.update {
                    it.copy(
                        currentSubTopicTitleId = R.string.relationship_building,
                        currentSubTopicsList = relationshipBuildingSubTopics,
                        currentBackgroundImage = R.drawable.relationship_building,
                    )
                }
            }

            TopicCategories.COOPERATION -> {
                _uiState.update {
                    it.copy(
                        currentSubTopicTitleId = R.string.cooperation,
                        currentSubTopicsList = cooperationSubTopics,
                        currentBackgroundImage = R.drawable.cooperation,
                    )
                }
            }

            TopicCategories.CONFLICT_RESOLUTION -> {
                _uiState.update {
                    it.copy(
                        currentSubTopicTitleId = R.string.conflict_resolution,
                        currentSubTopicsList = conflictResolutionSubTopics,
                        currentBackgroundImage = R.drawable.conflict_resolution,
                    )
                }
            }

            TopicCategories.PROBLEM_SOLVING -> {
                _uiState.update {
                    it.copy(
                        currentSubTopicTitleId = R.string.problem_solving,
                        currentSubTopicsList = problemSolvingSubTopics,
                        currentBackgroundImage = R.drawable.problem_solving,
                    )
                }
            }

            TopicCategories.SELF_AWARENESS -> {
                _uiState.update {
                    it.copy(
                        currentSubTopicTitleId = R.string.self_awareness,
                        currentSubTopicsList = selfAwarenessSubTopics,
                        currentBackgroundImage = R.drawable.self_awareness,
                    )
                }
            }

            TopicCategories.SELF_MANAGEMENT -> {
                _uiState.update {
                    it.copy(
                        currentSubTopicTitleId = R.string.self_management,
                        currentSubTopicsList = selfManagementSubTopics,
                        currentBackgroundImage = R.drawable.self_management,
                    )
                }
            }

            TopicCategories.EMPATHY -> {
                _uiState.update {
                    it.copy(
                        currentSubTopicTitleId = R.string.empathy,
                        currentSubTopicsList = empathySubTopics,
                        currentBackgroundImage = R.drawable.empathy,
                    )
                }
            }

            TopicCategories.ASSERTIVENESS -> {
                _uiState.update {
                    it.copy(
                        currentSubTopicTitleId = R.string.assertiveness,
                        currentSubTopicsList = assertivenessSubTopics,
                        currentBackgroundImage = R.drawable.assertiveness,
                    )
                }
            }

            else -> {}
        }
    }

    /**
     * This func updates the [com.samwrotethecode.socialease.ui.presentation.home.content.ReadingScreen] data
     */
    fun updateReadingScreenState(currentSubTopic: SubTopicsModel) {
        _uiState.update { it.copy(currentSubTopic = currentSubTopic) }
    }

    fun logOut() {
        auth.signOut()
    }

    fun deleteAccount() {
        viewModelScope.launch {
            userDataReference.delete().addOnSuccessListener {
                currentUser?.delete()
                logOut()
            }.addOnFailureListener {
                // failed to delete user data if it does not exist or no internet
                // the user is aware that internet connection is needed for this event
                logOut()
            }
        }
    }
}