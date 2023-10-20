package com.samwrotethecode.socialease.ui.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.samwrotethecode.socialease.R
import com.samwrotethecode.socialease.ui.presentation.home.home_screen_composables.HomeScreenAppBar
import com.samwrotethecode.socialease.ui.presentation.home.home_screen_composables.HomeScreenBody
import com.samwrotethecode.socialease.ui.presentation.home.home_screen_composables.HomeScreenDrawer
import com.samwrotethecode.socialease.ui.presentation.home.viewmodels.HomeScreenViewModel
import com.samwrotethecode.socialease.ui.presentation.navigation.Screens
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navHostController: NavHostController,
    windowSize: WindowWidthSizeClass,
    viewModel: HomeScreenViewModel,
) {
    val drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.intro_img_1),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            alpha = 0.1f,
        )

        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                HomeScreenDrawer(
                    navHostController = navHostController,
                    homeScreenViewModel = viewModel,
                )
            },
            gesturesEnabled = true,
        ) {
            Scaffold(
                topBar = {
                    HomeScreenAppBar(
                        navHostController = navHostController,
                        onNavigationClick = {
                            coroutineScope.launch {
                                drawerState.open()
                            }
                        },
                    ) {
                        navHostController.navigate(Screens.SearchScreen.route)
                    }
                },
                containerColor = Color.Transparent,
            ) {
                HomeScreenBody(
                    paddingValues = it,
                    navHostController = navHostController,
                    windowSize = windowSize,
                    viewModel = viewModel,
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        navHostController = rememberNavController(),
        windowSize = WindowWidthSizeClass.Compact,
        viewModel = viewModel<HomeScreenViewModel>(),
    )
}
