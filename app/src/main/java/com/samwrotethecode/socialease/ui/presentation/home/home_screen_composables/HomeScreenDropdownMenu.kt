package com.samwrotethecode.socialease.ui.presentation.home.home_screen_composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
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
import com.samwrotethecode.socialease.R
import com.samwrotethecode.socialease.ui.presentation.home.viewmodels.HomeScreenViewModel
import com.samwrotethecode.socialease.ui.presentation.home.viewmodels.HomeUiStateModel
import com.samwrotethecode.socialease.ui.presentation.navigation.Screens


@Composable
fun HomeScreenDropdownMenu(
    uiState: HomeUiStateModel,
    viewModel: HomeScreenViewModel,
    navHostController: NavHostController,
) {
    DropdownMenu(
        expanded = uiState.showDropdownMenu,
        onDismissRequest = { viewModel.updateAppbarDropDownMenu() }
    ) {
        DropdownMenuItem(
            text = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Icon(imageVector = Icons.Outlined.Info, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = stringResource(R.string.details))
                }
            },
            onClick = {
                viewModel.updateAppbarDropDownMenu()
                navHostController.navigate(Screens.SettingsScreen.route) {
                    launchSingleTop = true
                }
            }
        )
        DropdownMenuItem(
            text = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Icon(imageVector = Icons.Outlined.Settings, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = stringResource(R.string.settings))
                }
            },
            onClick = {
                viewModel.updateAppbarDropDownMenu()
                navHostController.navigate(Screens.SettingsScreen.route) {
                    launchSingleTop = true
                }
            }
        )
    }
}

@Preview
@Composable
fun HomeScreenDropdownMenuPreview() {
    HomeScreenDropdownMenu(
        uiState = viewModel<HomeScreenViewModel>().uiState.collectAsState().value,
        viewModel = viewModel<HomeScreenViewModel>(),
        navHostController = rememberNavController(),
    )
}