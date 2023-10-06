package com.samwrotethecode.socialease.ui.presentation.navigation

sealed class Screens(val route: String) {
    object IntroScreen : Screens("intro_screen")
    object SignInScreen : Screens("sign_in_screen")
    object RegisterScreen : Screens("register_screen")
    object ForgotPasswordScreen : Screens("forgot_password_screen")
    object HomeScreen : Screens("home_screen")
    object ProfileScreen : Screens("profile_screen")
    object FeedbackScreen : Screens("feedback_screen")
    object AboutScreen : Screens("about_screen")
    object SearchScreen : Screens("search_screen")
    object SettingsScreen : Screens("settings_screen")
}