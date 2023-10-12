package com.samwrotethecode.socialease.data.local_data

/**
 * [SubTopicContentItemModel] is a single entity inside a sub topic.
 * It is used to describe a single subject in a sub topic, and can
 * hold a paragraph, if the title is null.
 */
data class SubTopicContentItemModel(
    val title: String?,
    val description: String,
)

/**
 * [SubTopicsModel] describes how a subtopic should be as a child of a topic,
 * since all Topics have a list or children Sub topics of <[SubTopicsModel]>
 */
data class SubTopicsModel(
    val titleId: Int,
    val generalDescriptionId: Int,
    val content: List<SubTopicContentItemModel>
)

var communicationSubTopics = listOf<SubTopicsModel>()
var cooperationSubTopics = listOf<SubTopicsModel>()
var conflictResolutionSubTopics = listOf<SubTopicsModel>()
var problemSolvingSubTopics = listOf<SubTopicsModel>()
var selfAwarenessSubTopics = listOf<SubTopicsModel>()
var relationshipBuildingSubTopics = listOf<SubTopicsModel>()
var selfManagementSubTopics = listOf<SubTopicsModel>()
var empathySubTopics = listOf<SubTopicsModel>()
var assertivenessSubTopics = listOf<SubTopicsModel>()
