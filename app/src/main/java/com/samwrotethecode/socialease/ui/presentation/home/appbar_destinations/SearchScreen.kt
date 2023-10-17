package com.samwrotethecode.socialease.ui.presentation.home.appbar_destinations

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.SearchOff
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.samwrotethecode.socialease.R
import com.samwrotethecode.socialease.ui.presentation.home.viewmodels.SearchScreenViewModel

@Composable
fun SearchScreen(
    navHostController: NavHostController,
    viewModel: SearchScreenViewModel,
) {
    val uiState = viewModel.uiState.collectAsState().value
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.padding(16.dp)
        ) {
            TextField(value = uiState.query,
                onValueChange = {
                    viewModel.updateSearchQuery(it)
                    viewModel.startSearch(context = context)
                },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .widthIn(max = 600.dp, min = 420.dp),
                leadingIcon = {
                    IconButton(onClick = { navHostController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(id = R.string.navigate_back)
                        )
                    }
                },
                placeholder = {
                    Text(text = stringResource(id = R.string.search))
                },
                trailingIcon = {
                    IconButton(onClick = { viewModel.startSearch(context = context) }) {
                        Icon(imageVector = Icons.Outlined.Search, contentDescription = "Search")
                    }
                })
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
                for (item in uiState.searchResults) {
                    Text(text = stringResource(id = item.titleId))
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
        viewModel = viewModel<SearchScreenViewModel>(),
    )
}