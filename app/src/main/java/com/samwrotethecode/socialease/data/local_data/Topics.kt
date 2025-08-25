package com.samwrotethecode.socialease.data.local_data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.samwrotethecode.socialease.R
import com.samwrotethecode.socialease.ui.presentation.home.viewmodels.TopicCategories


data class HomeBodyItemModel(
    @param:DrawableRes val imageDrawable: Int,
    @param:StringRes val title: Int,
    @param:StringRes val description: Int,
    val topicCategory: TopicCategories,
)

val homeScreenBodyData = listOf(
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
