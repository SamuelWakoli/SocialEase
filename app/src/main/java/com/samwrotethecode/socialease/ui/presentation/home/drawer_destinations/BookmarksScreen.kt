package com.samwrotethecode.socialease.ui.presentation.home.drawer_destinations

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.CollectionsBookmark
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.samwrotethecode.socialease.R
import com.samwrotethecode.socialease.data.local_data.SubTopicsModel
import com.samwrotethecode.socialease.ui.presentation.home.content.SubTopicListItem
import com.samwrotethecode.socialease.ui.presentation.home.viewmodels.BookmarksScreenViewModel
import com.samwrotethecode.socialease.ui.presentation.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun BookmarksScreen(
    navHostController: NavHostController,
    viewModel: BookmarksScreenViewModel,
    windowWidthSize: WindowWidthSizeClass,
) {
    val uiState = viewModel.uiState.collectAsState().value

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.intro_img_1),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            alpha = 0.2f,
        )
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(text = stringResource(id = R.string.bookmarks)) },
                    navigationIcon = {
                        IconButton(
                            onClick = { navHostController.navigateUp() },
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = stringResource(id = R.string.navigate_back),
                            )

                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.Transparent,
                        actionIconContentColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                        navigationIconContentColor = MaterialTheme.colorScheme.primary,
                    )
                )
            }
        ) { paddingValues ->

            if (uiState.topicsList != null) {

                if (windowWidthSize == WindowWidthSizeClass.Compact) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        items(uiState.topicsList.size) { index ->
                            SubTopicListItem(
                                title = stringResource(id = uiState.topicsList[index].titleId),
                                generalDescription = stringResource(id = uiState.topicsList[index].generalDescriptionId),
                                windowSize = windowWidthSize,
                                onClick = {
                                    navHostController.navigate(Screens.ReadingScreen.route) {
                                        launchSingleTop = true
                                        viewModel.updateReadingScreenState(
                                            SubTopicsModel(
                                                titleId = uiState.topicsList[index].titleId,
                                                generalDescriptionId = uiState.topicsList[index].generalDescriptionId,
                                                content = uiState.topicsList[index].content,
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
                            horizontalArrangement = Arrangement.Center,
                            maxItemsInEachColumn = when (windowWidthSize) {
                                //                      WindowWidthSizeClass.Compact has been used at the top outer if branch
                                WindowWidthSizeClass.Medium -> uiState.topicsList.size / 2 + 1
                                WindowWidthSizeClass.Expanded -> uiState.topicsList.size / 4 + 1
                                else -> uiState.topicsList.size
                            }
                        ) {
                            for (item in uiState.topicsList) {
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
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.CollectionsBookmark,
                        contentDescription = null,
                        modifier = Modifier.size(128.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = stringResource(R.string.no_bookmarks_added),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
            }

        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun BookmarksScreenPreview() {
    BookmarksScreen(
        navHostController = rememberNavController(),
        viewModel = viewModel<BookmarksScreenViewModel>(),
        windowWidthSize = WindowWidthSizeClass.Compact
    )
}