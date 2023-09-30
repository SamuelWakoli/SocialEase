package com.samwrotethecode.socialease.ui.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.samwrotethecode.socialease.ui.presentation.start.IntroScreen

@Composable
fun NavGraph(navHostController: NavHostController, startDestination: String) {
    NavHost(navController = navHostController, startDestination = startDestination) {
        composable(route = Screens.IntroScreen.route) {
            IntroScreen()
        }
    }
}

@Preview
@Composable
fun NavGraphPreview() {
    NavGraph(rememberNavController(), Screens.IntroScreen.route)
}