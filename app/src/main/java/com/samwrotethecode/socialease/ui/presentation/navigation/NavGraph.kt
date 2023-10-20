package com.samwrotethecode.socialease.ui.presentation.navigation

import android.app.Activity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.samwrotethecode.socialease.ui.presentation.home.HomeScreen
import com.samwrotethecode.socialease.ui.presentation.home.appbar_destinations.SearchScreen
import com.samwrotethecode.socialease.ui.presentation.home.appbar_destinations.SettingsScreen
import com.samwrotethecode.socialease.ui.presentation.home.content.ReadingScreen
import com.samwrotethecode.socialease.ui.presentation.home.content.SubTopicsScreen
import com.samwrotethecode.socialease.ui.presentation.home.drawer_destinations.AboutScreen
import com.samwrotethecode.socialease.ui.presentation.home.drawer_destinations.BookmarksScreen
import com.samwrotethecode.socialease.ui.presentation.home.drawer_destinations.FeedbackScreen
import com.samwrotethecode.socialease.ui.presentation.home.drawer_destinations.ProfileScreen
import com.samwrotethecode.socialease.ui.presentation.home.viewmodels.FeedbackScreenViewmodel
import com.samwrotethecode.socialease.ui.presentation.home.viewmodels.HomeScreenViewModel
import com.samwrotethecode.socialease.ui.presentation.home.viewmodels.SearchScreenViewModel
import com.samwrotethecode.socialease.ui.presentation.home.viewmodels.SignInScreenViewModel
import com.samwrotethecode.socialease.ui.presentation.start.ForgotPasswordScreen
import com.samwrotethecode.socialease.ui.presentation.start.IntroScreen
import com.samwrotethecode.socialease.ui.presentation.start.RegisterScreen
import com.samwrotethecode.socialease.ui.presentation.start.SignInScreen

@Composable
fun NavGraph(
    navHostController: NavHostController,
    startDestination: String,
    windowSize: WindowWidthSizeClass,
    signInScreenViewModel: SignInScreenViewModel, //elevated this param to track GoogleSign in result
    onSignInWithGoogle: () -> Unit,
) {
    val homeScreenViewModel = viewModel<HomeScreenViewModel>()
    val searchScreenViewModel = viewModel<SearchScreenViewModel>()
    val feedbackScreenViewModel = viewModel<FeedbackScreenViewmodel>()


    NavHost(navController = navHostController, startDestination = startDestination) {
        composable(route = Screens.IntroScreen.route) {
            IntroScreen(
                windowSize = windowSize,
                navHostController = navHostController
            )
        }
        composable(route = Screens.SignInScreen.route) {
            SignInScreen(
                windowSize = windowSize,
                navHostController = navHostController,
                viewModel = signInScreenViewModel,
            ) {
                onSignInWithGoogle()
            }
        }
        composable(route = Screens.RegisterScreen.route) {
            RegisterScreen(
                windowSize = windowSize,
                navHostController = navHostController,
                viewModel = signInScreenViewModel,
            ) {
                onSignInWithGoogle()
            }
        }
        composable(route = Screens.ForgotPasswordScreen.route) {
            ForgotPasswordScreen(
                navHostController = navHostController,
                viewModel = signInScreenViewModel,
            )
        }
        composable(route = Screens.HomeScreen.route) {
            HomeScreen(
                navHostController = navHostController,
                windowSize = windowSize,
                viewModel = homeScreenViewModel,
            )
        }
        composable(route = Screens.ProfileScreen.route) {
            ProfileScreen(
                navHostController = navHostController,
                viewModel = homeScreenViewModel,
            )
        }
        composable(route = Screens.FeedbackScreen.route) {
            FeedbackScreen(
                navHostController = navHostController,
                viewModel = feedbackScreenViewModel,
            )
        }
        composable(route = Screens.BookmarksScreen.route) {
            BookmarksScreen(
                navHostController = navHostController,
                viewModel = homeScreenViewModel,
                windowWidthSize = windowSize
            )
        }
        composable(route = Screens.AboutScreen.route) {
            AboutScreen(
                navHostController = navHostController,
            )
        }
        composable(route = Screens.SearchScreen.route) {
            SearchScreen(
                navHostController = navHostController,
                searchScreenViewModel = searchScreenViewModel,
                homeScreenViewModel = homeScreenViewModel,
            )
        }
        composable(route = Screens.SubTopicsScreen.route) {
            SubTopicsScreen(
                navHostController = navHostController,
                homeScreenViewModel = homeScreenViewModel,
                windowWidthSize = windowSize
            )
        }
        composable(route = Screens.ReadingScreen.route) {
            ReadingScreen(
                navHostController = navHostController,
                homeScreenViewModel = homeScreenViewModel,
            )
        }
        composable(route = Screens.SettingsScreen.route) {
            SettingsScreen(
                navHostController = navHostController,
            )
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
        ).widthSizeClass,
        signInScreenViewModel = viewModel<SignInScreenViewModel>(),
        onSignInWithGoogle = {}
    )
}