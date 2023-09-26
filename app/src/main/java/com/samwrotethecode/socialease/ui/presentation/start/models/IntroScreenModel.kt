package com.samwrotethecode.socialease.ui.presentation.start.models

import androidx.annotation.DrawableRes
import com.samwrotethecode.socialease.R

data class IntroScreenModel(
    val title: String,
    val bodyText: String,
    @DrawableRes val backgroundImage: Int,
)

val IntroScreenData = listOf<IntroScreenModel>(
//    IntroScreenModel(
//        "SocialEase",
//        "",
//        R.drawable.
//    ),
)