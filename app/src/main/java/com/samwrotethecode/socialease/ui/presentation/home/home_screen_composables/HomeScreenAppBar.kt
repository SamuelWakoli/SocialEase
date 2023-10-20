package com.samwrotethecode.socialease.ui.presentation.home.home_screen_composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.samwrotethecode.socialease.R
import com.samwrotethecode.socialease.ui.presentation.navigation.Screens
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenAppBar(
    navHostController: NavHostController,
    onNavigationClick: () -> Unit,
    onSearchClick: () -> Unit,
    ) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                color = MaterialTheme.colorScheme.primary
            )
        },
        navigationIcon = {
            IconButton(onClick = { onNavigationClick() }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = stringResource(R.string.open_navigation_menu)
                )
            }
        },
        actions = {
            IconButton(onClick = { onSearchClick() }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.search)
                )
            }
            IconButton(onClick = {
                navHostController.navigate(Screens.BookmarksScreen.route) {
                    launchSingleTop = true
                }
            }) {
                Icon(
                    imageVector = Icons.Outlined.Bookmarks,
                    contentDescription = stringResource(R.string.bookmarks)
                )
            }
//            IconButton(onClick = { viewModel.updateAppbarDropDownMenu() }) {
//                Icon(
//                    imageVector = Icons.Default.MoreVert,
//                    contentDescription = stringResource(R.string.show_popup_menu)
//                )
//                HomeScreenDropdownMenu(
//                    uiState = uiState,
//                    viewModel = viewModel,
//                    navHostController = navHostController,
//                )
//            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent,
            actionIconContentColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.primary,
            navigationIconContentColor = MaterialTheme.colorScheme.primary,
        )
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenAppBarPreview() {
    val coroutineScope = rememberCoroutineScope()

    HomeScreenAppBar(
        navHostController = rememberNavController(),
        onNavigationClick = {
            coroutineScope.launch { }
        },
    ) {}
}
