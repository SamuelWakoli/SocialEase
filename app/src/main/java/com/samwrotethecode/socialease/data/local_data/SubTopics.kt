package com.samwrotethecode.socialease.data.local_data

/**
 * [SubTopicContentItemModel] is a single entity inside a sub topic.
 * It is used to describe a single subject in a sub topic, and can
 * hold a paragraph, if the title is null.
 */
data class SubTopicContentItemModel(
    val titleId: Int?,
    val descriptionId: Int?,
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

var cooperationSubTopics = listOf<SubTopicsModel>()
var conflictResolutionSubTopics = listOf<SubTopicsModel>()
var problemSolvingSubTopics = listOf<SubTopicsModel>()
var selfAwarenessSubTopics = listOf<SubTopicsModel>()
var relationshipBuildingSubTopics = listOf<SubTopicsModel>()
var selfManagementSubTopics = listOf<SubTopicsModel>()
var empathySubTopics = listOf<SubTopicsModel>()
var assertivenessSubTopics = listOf<SubTopicsModel>()

/**
 * To provide subtopics for each of the nine major topics you've listed, I can suggest specific
 * areas of focus within each category. Here are subtopics for each of the main topics:
 *
 * 1. **Communication**:
 *    - Verbal Communication
 *    - Nonverbal Communication
 *    - Active Listening
 *    - Body Language
 *    - Clear and Concise Speaking
 *    - Maintaining Eye Contact
 *    - Effective Use of Tone and Pitch
 *
 * 2. **Cooperation**:
 *    - Sharing
 *    - Compromise
 *    - Teamwork
 *    - Collaboration
 *    - Supporting Others
 *    - Group Decision-Making
 *
 * 3. **Conflict Resolution**:
 *    - Identifying Conflict
 *    - Active Listening in Conflicts
 *    - Negotiation Skills
 *    - Mediation
 *    - Conflict De-escalation
 *    - Finding Common Ground
 *
 * 4. **Problem Solving**:
 *    - Problem Identification
 *    - Critical Thinking
 *    - Analyzing Options
 *    - Decision-Making
 *    - Implementation of Solutions
 *    - Evaluation of Outcomes
 *
 * 5. **Self Awareness**:
 *    - Self-Reflection
 *    - Recognizing Emotions
 *    - Understanding Personal Values
 *    - Self-Identity
 *    - Self-Improvement
 *    - Self-Regulation
 *
 * 6. **Relationship Building**:
 *    - Building Trust
 *    - Building Rapport
 *    - Active Listening in Relationships
 *    - Empathy in Relationships
 *    - Conflict Resolution in Relationships
 *    - Supporting Friends and Partners
 *
 * 7. **Self Management**:
 *    - Emotional Regulation
 *    - Stress Management
 *    - Time Management
 *    - Goal Setting
 *    - Self-Care
 *    - Handling Pressure
 *
 * 8. **Empathy**:
 *    - Cognitive Empathy (Understanding Others' Perspectives)
 *    - Emotional Empathy (Sharing Others' Feelings)
 *    - Compassion
 *    - Empathetic Communication
 *    - Empathetic Listening
 *    - Responding to Others' Needs
 *
 * 9. **Assertiveness**:
 *    - Expressing Needs Clearly
 *    - Setting Boundaries
 *    - Conflict Resolution with Assertiveness
 *    - Respecting Others' Rights
 *    - Self-Advocacy
 *    - Confidence Building
 *
 * These subtopics provide a more detailed breakdown of what users can explore and learn within
 * each major skill area. It allows for a structured approach to improving social skills and
 * interpersonal relationships.
 */