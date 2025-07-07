package com.samwrotethecode.socialease.ui.presentation.home.drawer_destinations

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.samwrotethecode.socialease.R
import com.samwrotethecode.socialease.data.local_data.SubTopicsModel
import com.samwrotethecode.socialease.ui.presentation.home.content.SubTopicListItem
import com.samwrotethecode.socialease.ui.presentation.home.viewmodels.HomeScreenViewModel
import com.samwrotethecode.socialease.ui.presentation.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun BookmarksScreen(
    navHostController: NavHostController,
    viewModel: HomeScreenViewModel,
    windowWidthSize: WindowWidthSizeClass,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val uiState = viewModel.uiState.collectAsState().value
    val context = LocalContext.current
    fun shareSubtopic(subTopicsModel: SubTopicsModel) {
        val title = ContextCompat.getString(context, subTopicsModel.titleId)
        ContextCompat.getString(context, subTopicsModel.generalDescriptionId)
        var message = ""
        for (tile in subTopicsModel.content) {
            message += ContextCompat.getString(
                context,
                tile.titleId!!
            ) + "\n" + ContextCompat.getString(
                context, tile.descriptionId!!
            ) + "\n\n"
        }

        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TITLE, title)
            putExtra(Intent.EXTRA_TEXT, message)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        context.startActivity(shareIntent)
    }

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
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            containerColor = Color.Transparent,
            topBar = {
                CenterAlignedTopAppBar(
                    scrollBehavior = scrollBehavior,
                    title = { Text(text = stringResource(id = R.string.bookmarks)) },
                    navigationIcon = {
                        IconButton(
                            onClick = { navHostController.navigateUp() },
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
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
            if (!uiState.bookmarksIds.isNullOrEmpty() && uiState.bookmarks.isNotEmpty()) {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    items(uiState.bookmarksIds!!.size) { index ->
                        SubTopicListItem(
                            bookmarksIds = uiState.bookmarksIds,
                            titleId = uiState.bookmarks[index].titleId,
                            generalDescriptionId = uiState.bookmarks[index].generalDescriptionId,
                            windowSize = windowWidthSize,
                            onClick = {
                                navHostController.navigate(Screens.ReadingScreen.route) {
                                    launchSingleTop = true
                                    viewModel.updateReadingScreenState(
                                        SubTopicsModel(
                                            titleId = uiState.bookmarks[index].titleId,
                                            generalDescriptionId = uiState.bookmarks[index].generalDescriptionId,
                                            content = uiState.bookmarks[index].content,
                                        )
                                    )
                                }
                            },
                            onClickShare = { shareSubtopic(uiState.bookmarks[index]) },
                            onClickBookmark = {
                                viewModel.updateBookmark(
                                    subtopic = uiState.bookmarks[index],
                                    isBookmarked = it
                                )
                            },
                        )
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
        viewModel = viewModel<HomeScreenViewModel>(),
        windowWidthSize = WindowWidthSizeClass.Compact
    )
}