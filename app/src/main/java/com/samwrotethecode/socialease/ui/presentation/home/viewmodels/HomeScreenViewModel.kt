package com.samwrotethecode.socialease.ui.presentation.home.viewmodels

import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.samwrotethecode.socialease.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class HomeUiStateModel(
    val photoUrl: Uri? = null,
    val displayName: String? = null,
    val email: String? = null,
    val showDropdownMenu: Boolean = false,
    val currentCategory: TopicCategories? = null,
)

enum class TopicCategories {
    COMMUNICATION,
    RELATIONSHIP_BUILDING,
    COOPERATION,
    CONFLICT_RESOLUTION,
    PROBLEM_SOLVING,
    SELF_AWARENESS,
    SELF_MANAGEMENT,
    EMPATHY,
    ASSERTIVENESS
}

class HomeScreenViewModel : ViewModel() {
    private var _uiState: MutableStateFlow<HomeUiStateModel> = MutableStateFlow(HomeUiStateModel())
    val uiState: StateFlow<HomeUiStateModel> = _uiState.asStateFlow()

    private val auth: FirebaseAuth = Firebase.auth
    private val currentUser: FirebaseUser? = auth.currentUser

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
