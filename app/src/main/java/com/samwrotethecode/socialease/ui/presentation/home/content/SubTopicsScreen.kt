package com.samwrotethecode.socialease.ui.presentation.home.content

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat.getString
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.samwrotethecode.socialease.R
import com.samwrotethecode.socialease.data.local_data.SubTopicsModel
import com.samwrotethecode.socialease.ui.presentation.home.viewmodels.HomeScreenViewModel
import com.samwrotethecode.socialease.ui.presentation.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun SubTopicsScreen(
    navHostController: NavHostController,
    homeScreenViewModel: HomeScreenViewModel,
    windowWidthSize: WindowWidthSizeClass,
) {

    val uiState = homeScreenViewModel.uiState.collectAsState().value
    val context = LocalContext.current

    fun shareSubtopic(subTopicsModel: SubTopicsModel) {
        Log.d("SHARE DATA", "Data title: ${getString(context, subTopicsModel.titleId)}")
        val title = getString(context, subTopicsModel.titleId)
        getString(context, subTopicsModel.generalDescriptionId)
        var message = ""
        for (tile in subTopicsModel.content) {
            message += getString(context, tile.titleId!!) + "\n" + getString(
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

    Box {
        Image(
            painter = painterResource(id = uiState.currentBackgroundImage!!),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            alpha = 0.2f,
        )
        Scaffold(modifier = Modifier.fillMaxSize(),
            containerColor = Color.Transparent,
            contentColor = Color.Transparent,
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
//                        Log.d(
//                            "CURRENT SUB TOPIC TITLE ID",
//                            "ACCESSING: ${uiState.currentSubTopicTitleId} "
//                        )
                        Text(text = stringResource(id = uiState.currentSubTopicTitleId!!))
                    },
                    navigationIcon = {
                        IconButton(onClick = { navHostController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = stringResource(id = R.string.navigate_back)
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent,
                    ),
                )
            }) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                items(uiState.currentSubTopicsList!!.size) { index ->
                    SubTopicListItem(
                        bookmarksIds = uiState.bookmarksIds,
                        titleId = uiState.currentSubTopicsList[index].titleId,
                        generalDescriptionId = uiState.currentSubTopicsList[index].generalDescriptionId,
                        windowSize = windowWidthSize,
                        onClick = {
                            navHostController.navigate(Screens.ReadingScreen.route) {
                                launchSingleTop = true
                                homeScreenViewModel.updateReadingScreenState(
                                    SubTopicsModel(
                                        titleId = uiState.currentSubTopicsList[index].titleId,
                                        generalDescriptionId = uiState.currentSubTopicsList[index].generalDescriptionId,
                                        content = uiState.currentSubTopicsList[index].content,
                                    )
                                )
                            }
                        },
                        onClickShare = { shareSubtopic(uiState.currentSubTopicsList[index]) },
                        onClickBookmark = {
                            homeScreenViewModel.updateBookmark(
                                subtopic = uiState.currentSubTopicsList[index],
                                isBookmarked = it
                            )
                        },
                    )
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
        homeScreenViewModel = viewModel<HomeScreenViewModel>(),
        windowWidthSize = WindowWidthSizeClass.Compact
    )
}