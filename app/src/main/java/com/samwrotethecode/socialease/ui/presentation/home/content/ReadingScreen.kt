package com.samwrotethecode.socialease.ui.presentation.home.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.samwrotethecode.socialease.ui.presentation.home.viewmodels.HomeScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReadingScreen(
    navHostController: NavHostController,
    viewModel: HomeScreenViewModel,
) {

    val uiState = viewModel.uiState.collectAsState().value
    val contentModifier = Modifier

    Scaffold(
        modifier = Modifier.widthIn(max = 800.dp),
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navHostController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Navigate back",
                        )
                    }
                },
                title = {
                    Text(
                        text = stringResource(id = uiState.currentSubTopic?.titleId!!)
                    )
                },
            )
        },
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
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

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ReadingScreenPreview() {
    ReadingScreen(
        navHostController = rememberNavController(),
        viewModel = viewModel<HomeScreenViewModel>(),
    )
}