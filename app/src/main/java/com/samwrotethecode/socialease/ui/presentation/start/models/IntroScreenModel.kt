package com.samwrotethecode.socialease.ui.presentation.start.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.samwrotethecode.socialease.R

data class IntroScreenModel(
    @StringRes val title: Int,
    @StringRes val subTitle: Int,
    @StringRes val content: Int,
    @DrawableRes val backgroundImage: Int,
)

val IntroScreenData: List<IntroScreenModel> = listOf(
    IntroScreenModel(
        title = R.string.welcome_to_socialease,
        subTitle = R.string.enhance_your_social_skills,
        content = R.string.welcome_to_socialease_content,
        backgroundImage = R.drawable.intro_img_1
    ),
    IntroScreenModel(
        title = R.string.what_socialease_offers,
        subTitle = R.string.your_comprehensive_social_skills_companion,
        content = R.string.what_socialease_offers_content,
        backgroundImage = R.drawable.intro_img_2
    ),
    IntroScreenModel(
        title = R.string.how_it_works,
        subTitle = R.string.simple_steps_to_success,
        content = R.string.how_it_works_content,
        backgroundImage = R.drawable.intro_img_3
    ),
    IntroScreenModel(
        title = R.string.start_your_journey,
        subTitle = R.string.let_s_get_started,
        content = R.string.start_your_journey_content,
        backgroundImage = R.drawable.intro_img_4
    ),
)