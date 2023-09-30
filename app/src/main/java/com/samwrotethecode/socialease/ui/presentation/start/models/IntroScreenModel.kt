package com.samwrotethecode.socialease.ui.presentation.start.models

import androidx.annotation.DrawableRes
import com.samwrotethecode.socialease.R

data class IntroScreenModel(
    val title: String,
    val subTitle: String,
    val content: String,
    @DrawableRes val backgroundImage: Int,
)

val IntroScreenData: List<IntroScreenModel> = listOf(
    IntroScreenModel(
        title = "Welcome to SocialEase",
        subTitle = "Enhance Your Social Skills",
        content = """
            Welcome to SocialEase, your personal guide to improving your social skills and boosting your confidence in social situations.
            Whether you're looking to excel in networking, make new friends, or enhance your professional relationships, SocialEase has you covered.
            Swipe right to get started on your journey to becoming a more socially confident you!
        """.trimIndent(),
        backgroundImage = R.drawable.intro_img_1
    ),
    IntroScreenModel(
        title = "What SocialEase Offers",
        subTitle = "Your Comprehensive Social Skills Companion",
        content = """
            SocialEase offers a wealth of resources and guidance to help you navigate the intricacies of social interactions.
            Explore a wide range of topics, including effective communication, active listening, body language, and more.
            Each topic is packed with tips, real-life examples, and practical exercises to help you apply what you learn in real-world situations.
        """.trimIndent(),
        backgroundImage = R.drawable.intro_img_2
    ),
    IntroScreenModel(
        title = "How It Works",
        subTitle = "Simple Steps to Success",
        content = """
            Using SocialEase is easy! Here's how it works:
            - Browse our extensive library of social skills topics.
            - Read insightful articles and tips to gain knowledge.
            - Practice what you've learned with interactive exercises.
            - Track your progress and see your social skills flourish.
            Swipe right to begin your journey towards becoming a more socially confident and skilled individual.
        """.trimIndent(),
        backgroundImage = R.drawable.intro_img_3
    ),
    IntroScreenModel(
        title = "Start Your Journey",
        subTitle = "Let's Get Started",
        content = """
            It's time to embark on your journey to improved social skills and greater self-confidence.
            Swipe right to start exploring our comprehensive library and discover how SocialEase can make a positive impact on your social interactions.
            Remember, every interaction is an opportunity to grow, and SocialEase is here to support you every step of the way.
        """.trimIndent(),
        backgroundImage = R.drawable.intro_img_4
    ),
)