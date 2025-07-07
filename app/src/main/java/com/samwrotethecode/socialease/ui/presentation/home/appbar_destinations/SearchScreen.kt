package com.samwrotethecode.socialease.ui.presentation.home.appbar_destinations

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.SearchOff
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.samwrotethecode.socialease.R
import com.samwrotethecode.socialease.data.local_data.SubTopicsModel
import com.samwrotethecode.socialease.ui.presentation.home.viewmodels.HomeScreenViewModel
import com.samwrotethecode.socialease.ui.presentation.home.viewmodels.SearchScreenViewModel
import com.samwrotethecode.socialease.ui.presentation.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    navHostController: NavHostController,
    searchScreenViewModel: SearchScreenViewModel,
    homeScreenViewModel: HomeScreenViewModel
) {
    val uiState = searchScreenViewModel.uiState.collectAsState().value
    val context = LocalContext.current
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(key1 = Unit, block = {
        focusRequester.requestFocus()
    })

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.intro_img_1),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            alpha = 0.1f
        )
        Column(
            modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier.padding(16.dp)
            ) {
                TextField(
                    value = uiState.query,
                    onValueChange = {
                        searchScreenViewModel.updateSearchQuery(it)
                        searchScreenViewModel.startSearch(context = context)
                    },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                        .widthIn(max = 600.dp, min = 420.dp)
                        .focusRequester(focusRequester = focusRequester)
                        .onFocusChanged {
                            if (it.isFocused) keyboardController?.show()
                        },
                    leadingIcon = {
                        IconButton(onClick = { navHostController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(id = R.string.navigate_back)
                            )
                        }
                    },
                    placeholder = {
                        Text(text = stringResource(id = R.string.search))
                    },
                    trailingIcon = {
                        IconButton(onClick = { searchScreenViewModel.startSearch(context = context) }) {
                            Icon(imageVector = Icons.Outlined.Search, contentDescription = "Search")
                        }
                    },
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            searchScreenViewModel.startSearch(context = context)
                        },
                    ),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Unspecified,
                        autoCorrectEnabled = true,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = if (uiState.searchResults.isEmpty()) Arrangement.Center else Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                if (uiState.searchResults.isEmpty()) {
                    Icon(
                        imageVector = Icons.Outlined.SearchOff,
                        contentDescription = null,
                        modifier = Modifier.size(64.dp),
                        tint = MaterialTheme.colorScheme.primary,
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = stringResource(R.string.no_results_found),
                        style = TextStyle(
                            fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                            fontWeight = MaterialTheme.typography.headlineSmall.fontWeight,
                            color = MaterialTheme.colorScheme.primary
                        ),
                    )
                } else {
                    for (subTopicsModelItem in uiState.searchResults) {
                        Card(
                            modifier = Modifier
                                .padding(vertical = 4.dp, horizontal = 8.dp)
                                .widthIn(max = 600.dp),
                            onClick = {
//                                Log.d(
//                                    "SEARCH SCREEN",
//                                    "CLICKED TITLE: ${
//                                        getString(context, subTopicsModelItem.titleId)
//                                    }"
//                                )
                                navHostController.navigate(Screens.ReadingScreen.route) {
                                    launchSingleTop = true
                                    popUpTo(Screens.SearchScreen.route) {
                                        inclusive = true
                                    }
                                    homeScreenViewModel.updateReadingScreenState(
                                        SubTopicsModel(
                                            titleId = subTopicsModelItem.titleId,
                                            generalDescriptionId = subTopicsModelItem.generalDescriptionId,
                                            content = subTopicsModelItem.content,
                                        )
                                    )
                                }
                            },
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer
                            ),
                        ) {
                            ListItem(headlineContent = {
                                Text(
                                    text = stringResource(id = subTopicsModelItem.titleId)
                                )
                            })
                        }
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
fun SearchScreenPreview() {
    SearchScreen(
        navHostController = rememberNavController(),
        searchScreenViewModel = viewModel<SearchScreenViewModel>(),
        homeScreenViewModel = viewModel<HomeScreenViewModel>()
    )
}