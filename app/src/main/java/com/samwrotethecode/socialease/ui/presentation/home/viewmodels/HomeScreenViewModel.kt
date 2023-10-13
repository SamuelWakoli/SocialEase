package com.samwrotethecode.socialease.ui.presentation.home.viewmodels

import android.net.Uri
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.samwrotethecode.socialease.R
import com.samwrotethecode.socialease.data.local_data.SubTopicsModel
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

data class HomeUiStateModel(
    val photoUrl: Uri? = null,
    val displayName: String? = null,
    val email: String? = null,
    val showDropdownMenu: Boolean = false,
    @DrawableRes val currentBackgroundImage: Int? = null,

    // SubTopicsScreen state
    val currentCategory: TopicCategories? = null,
    @StringRes val currentSubTopicTitleId: Int? = null,
    val currentSubTopicsList: List<SubTopicsModel>? = null,

    // ReadingScreen state
    val currentSubTopic: SubTopicsModel? = null,
    )

enum class TopicCategories {
    COMMUNICATION, RELATIONSHIP_BUILDING, COOPERATION, CONFLICT_RESOLUTION, PROBLEM_SOLVING, SELF_AWARENESS, SELF_MANAGEMENT, EMPATHY, ASSERTIVENESS
}

class HomeScreenViewModel : ViewModel() {
    private var _uiState: MutableStateFlow<HomeUiStateModel> = MutableStateFlow(HomeUiStateModel())
    val uiState: StateFlow<HomeUiStateModel> = _uiState.asStateFlow()

    private val auth: FirebaseAuth = Firebase.auth
    private val currentUser: FirebaseUser? = auth.currentUser

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
                Log.d(
                    "CURRENT SUB TOPIC TITLE ID",
                    "ASSIGNED: ${_uiState.value.currentSubTopicTitleId} "
                )
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
     * This func updates the [ReadingScreen] data
     */
    fun updateReadingScreenState(currentSubTopic: SubTopicsModel) {
        _uiState.update { it.copy(currentSubTopic = currentSubTopic) }
    }

    fun logOut() {
        auth.signOut()
    }

    fun deleteAccount() {
        currentUser?.delete()
        logOut()
    }
}


data class HomeBodyItemModel(
    @DrawableRes val imageDrawable: Int,
    @StringRes val title: Int,
    @StringRes val description: Int,
    val topicCategory: TopicCategories,
)

val homeScreenBodyData = listOf<HomeBodyItemModel>(
    HomeBodyItemModel(
        imageDrawable = R.drawable.communication,
        title = R.string.communication,
        description = R.string.communication_description,
        topicCategory = TopicCategories.COMMUNICATION,
    ),
    HomeBodyItemModel(
        imageDrawable = R.drawable.cooperation,
        title = R.string.cooperation,
        description = R.string.cooperation_description,
        topicCategory = TopicCategories.COOPERATION,
    ),
    HomeBodyItemModel(
        imageDrawable = R.drawable.conflict_resolution,
        title = R.string.conflict_resolution,
        description = R.string.conflict_resolution_description,
        topicCategory = TopicCategories.CONFLICT_RESOLUTION,
    ),
    HomeBodyItemModel(
        imageDrawable = R.drawable.problem_solving,
        title = R.string.problem_solving,
        description = R.string.problem_solving_description,
        topicCategory = TopicCategories.PROBLEM_SOLVING,
    ),
    HomeBodyItemModel(
        imageDrawable = R.drawable.self_awareness,
        title = R.string.self_awareness,
        description = R.string.self_awareness_description,
        topicCategory = TopicCategories.SELF_AWARENESS,
    ),
    HomeBodyItemModel(
        imageDrawable = R.drawable.relationship_building,
        title = R.string.relationship_building,
        description = R.string.relationship_building_description,
        topicCategory = TopicCategories.RELATIONSHIP_BUILDING,
    ),
    HomeBodyItemModel(
        imageDrawable = R.drawable.self_management,
        title = R.string.self_management,
        description = R.string.self_management_description,
        topicCategory = TopicCategories.SELF_MANAGEMENT,
    ),
    HomeBodyItemModel(
        imageDrawable = R.drawable.empathy,
        title = R.string.empathy,
        description = R.string.empathy_description,
        topicCategory = TopicCategories.EMPATHY,
    ),
    HomeBodyItemModel(
        imageDrawable = R.drawable.assertiveness,
        title = R.string.assertiveness,
        description = R.string.assertiveness_description,
        topicCategory = TopicCategories.ASSERTIVENESS,
    ),
)
