package com.samwrotethecode.socialease.ui.presentation.home.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.BookmarkAdd
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.samwrotethecode.socialease.ui.presentation.home.viewmodels.HomeScreenViewModel
import com.samwrotethecode.socialease.ui.presentation.home.viewmodels.HomeUiStateModel


@Composable
fun ReadingScreenDropdownMenu(
    uiState: HomeUiStateModel,
    viewModel: HomeScreenViewModel,
    navHostController: NavHostController,
    isCurrentSubtopicSaved: Boolean = false,
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
                    Icon(
                        imageVector =
                        if (isCurrentSubtopicSaved) Icons.Outlined.Bookmark
                        else Icons.Outlined.BookmarkAdd, contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text =
                        if (isCurrentSubtopicSaved) "Remove from bookmarks"
                        else "Add to bookmarks"
                    )
                }
            },
            onClick = {
                viewModel.updateAppbarDropDownMenu()
                // TODO: Implement bookmarking logic
            }
        )
        DropdownMenuItem(
            text = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Icon(
                        imageVector =
                        Icons.Outlined.Share, contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Share"
                    )
                }
            },
            onClick = {
                viewModel.updateAppbarDropDownMenu()
                // TODO: Implement share logic
            }
        )
    }
}

@Preview
@Composable
fun ReadingScreenDropdownMenuPreview() {
    ReadingScreenDropdownMenu(
        uiState = viewModel<HomeScreenViewModel>().uiState.collectAsState().value,
        viewModel = viewModel<HomeScreenViewModel>(),
        navHostController = rememberNavController(),
    )
}