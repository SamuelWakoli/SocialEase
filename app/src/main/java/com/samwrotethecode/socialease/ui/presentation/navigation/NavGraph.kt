package com.samwrotethecode.socialease.ui.presentation.navigation

import android.app.Activity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.samwrotethecode.socialease.ui.presentation.start.IntroScreen

@Composable
fun NavGraph(
    navHostController: NavHostController,
    startDestination: String,
    windowSize: WindowWidthSizeClass
) {
    NavHost(navController = navHostController, startDestination = startDestination) {
        composable(route = Screens.IntroScreen.route) {
            IntroScreen(windowSize = windowSize)
        }
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview
@Composable
fun NavGraphPreview() {
    NavGraph(
        navHostController = rememberNavController(),
        startDestination = Screens.IntroScreen.route,
        windowSize = calculateWindowSizeClass(
            LocalContext.current as Activity
        ).widthSizeClass
    )
}