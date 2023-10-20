package com.samwrotethecode.socialease.ui.presentation.home.content

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import com.samwrotethecode.socialease.ui.presentation.home.viewmodels.HomeScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReadingScreen(
    navHostController: NavHostController,
    homeScreenViewModel: HomeScreenViewModel,
) {

    val uiState = homeScreenViewModel.uiState.collectAsState().value
    val contentModifier = Modifier

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


    Box {
        Image(
            painterResource(id = uiState.currentBackgroundImage ?: R.drawable.intro_img_1),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            alpha = 0.2f
        )

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.Transparent,
            contentColor = Color.Transparent,
            topBar = {
                CenterAlignedTopAppBar(
                    navigationIcon = {
                        IconButton(onClick = { navHostController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = stringResource(id = R.string.navigate_back),
                            )
                        }
                    },
                    title = {
                        Text(
                            text = stringResource(id = uiState.currentSubTopic?.titleId!!)
                        )
                    },
                    actions = {
                        IconButton(onClick = { homeScreenViewModel.updateAppbarDropDownMenu() }) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "Options"
                            )
                            ReadingScreenDropdownMenu(
                                uiState = uiState,
                                viewModel = homeScreenViewModel,
                                bookmarksIds = uiState.bookmarksIds,
                                titleId = uiState.currentSubTopic?.titleId!!,
                                onClickBookmark = {
                                    homeScreenViewModel.updateBookmark(
                                        subtopic = uiState.currentSubTopic,
                                        isBookmarked = it
                                    )
                                },
                            ) { shareSubtopic(uiState.currentSubTopic) }
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        navigationIconContentColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                        actionIconContentColor = MaterialTheme.colorScheme.primary,
                        containerColor = Color.Transparent,
                    ),
                )
            },
        ) { paddingValues ->
            Column(
                Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.surface)
                    .clip(MaterialTheme.shapes.medium),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(paddingValues = paddingValues)
                        .widthIn(max = 800.dp)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    items(count = uiState.currentSubTopic!!.content.size) { index ->
                        ReadingScreenListItem(
                            modifier = contentModifier,
                            title = stringResource(id = uiState.currentSubTopic.content[index].titleId!!),
                            description = stringResource(id = uiState.currentSubTopic.content[index].descriptionId!!),
                        )
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ReadingScreenPreview() {
    ReadingScreen(
        navHostController = rememberNavController(),
        homeScreenViewModel = viewModel<HomeScreenViewModel>(),
    )
}