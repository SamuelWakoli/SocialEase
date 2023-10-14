package com.samwrotethecode.socialease.ui.presentation.home.content

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.samwrotethecode.socialease.data.local_data.SubTopicsModel
import com.samwrotethecode.socialease.ui.presentation.home.viewmodels.HomeScreenViewModel
import com.samwrotethecode.socialease.ui.presentation.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun SubTopicsScreen(
    navHostController: NavHostController,
    viewModel: HomeScreenViewModel,
    windowWidthSize: WindowWidthSizeClass,
) {

    val uiState = viewModel.uiState.collectAsState().value

    Box {
        Image(
            painter = painterResource(id = uiState.currentBackgroundImage!!),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            alpha = 0.2f,
        )
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.Transparent,
            contentColor = Color.Transparent,
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Log.d(
                            "CURRENT SUB TOPIC TITLE ID",
                            "ACCESSING: ${uiState.currentSubTopicTitleId} "
                        )
                        Text(text = stringResource(id = uiState.currentSubTopicTitleId!!))
                    },
                    navigationIcon = {
                        IconButton(onClick = { navHostController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Navigate back"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent,
                    ),
                )
            }
        ) { paddingValues ->
            if (windowWidthSize == WindowWidthSizeClass.Compact) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    items(uiState.currentSubTopicsList!!.size) { index ->
                        SubTopicListItem(
                            title = stringResource(id = uiState.currentSubTopicsList[index].titleId),
                            generalDescription = stringResource(id = uiState.currentSubTopicsList[index].generalDescriptionId),
                            windowSize = windowWidthSize,
                            onClick = {
                                navHostController.navigate(Screens.ReadingScreen.route) {
                                    launchSingleTop = true
                                    viewModel.updateReadingScreenState(
                                        SubTopicsModel(
                                            titleId = uiState.currentSubTopicsList[index].titleId,
                                            generalDescriptionId = uiState.currentSubTopicsList[index].generalDescriptionId,
                                            content = uiState.currentSubTopicsList[index].content,
                                        )
                                    )
                                }
                            }
                        )
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    FlowColumn(
                        verticalArrangement = Arrangement.Top,
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        maxItemsInEachColumn = when (windowWidthSize) {
                            //                      WindowWidthSizeClass.Compact has been used at the top outer if branch
                            WindowWidthSizeClass.Medium -> uiState.currentSubTopicsList!!.size / 2 + 1
                            WindowWidthSizeClass.Expanded -> uiState.currentSubTopicsList!!.size / 4 + 3
                            else -> uiState.currentSubTopicsList!!.size
                        }
                    ) {
                        for (item in uiState.currentSubTopicsList) {
                            SubTopicListItem(
                                title = stringResource(id = item.titleId),
                                generalDescription = stringResource(id = item.generalDescriptionId),
                                windowSize = windowWidthSize,
                                onClick = {
                                    navHostController.navigate(Screens.ReadingScreen.route) {
                                        launchSingleTop = true
                                        viewModel.updateReadingScreenState(
                                            SubTopicsModel(
                                                titleId = item.titleId,
                                                generalDescriptionId = item.generalDescriptionId,
                                                content = item.content,
                                            )
                                        )
                                    }
                                })
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SubTopicsScreenPreview() {
    SubTopicsScreen(
        navHostController = rememberNavController(),
        viewModel = viewModel<HomeScreenViewModel>(),
        windowWidthSize = WindowWidthSizeClass.Compact
    )
}