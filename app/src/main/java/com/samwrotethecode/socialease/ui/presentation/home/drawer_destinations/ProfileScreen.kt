package com.samwrotethecode.socialease.ui.presentation.home.drawer_destinations

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.PersonRemoveAlt1
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.samwrotethecode.socialease.R
import com.samwrotethecode.socialease.ui.presentation.composables.CoilImage
import com.samwrotethecode.socialease.ui.presentation.composables.CustomDialogBox
import com.samwrotethecode.socialease.ui.presentation.home.viewmodels.HomeScreenViewModel
import com.samwrotethecode.socialease.ui.presentation.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navHostController: NavHostController,
    viewModel: HomeScreenViewModel,
) {

    val uiState = viewModel.uiState.collectAsState().value
    var showLogOutDialog by remember { mutableStateOf(false) }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(text = stringResource(R.string.profile))
            },
            navigationIcon = {
                IconButton(onClick = { navHostController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.navigate_back)
                    )
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                actionIconContentColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.primary,
                navigationIconContentColor = MaterialTheme.colorScheme.primary,
            )
        )
    }) {
        Box(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .padding(horizontal = 16.dp)
                    .widthIn(max = 600.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center,
                ) {
                    CoilImage(
                        photoUrl = uiState.photoUrl, size = 160.dp
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                ListItem(
                    headlineContent = {
                        Text(
                            text = uiState.displayName ?: "No name"
                        )
                    },
                    supportingContent = {
                        Text(
                            text =
                            uiState.email ?: ""
                        )
                    },
                )
                Spacer(modifier = Modifier.height(8.dp))
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer
                    ),
                    onClick = {
                        showLogOutDialog = !showLogOutDialog
                    },
                ) {
                    ListItem(
                        leadingContent = {
                            Icon(
                                imageVector = Icons.Outlined.Logout, contentDescription = null
                            )
                        },
                        headlineContent = { Text(text = stringResource(R.string.log_out)) },
                        colors = ListItemDefaults.colors(
                            leadingIconColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            headlineColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            containerColor = Color.Transparent,
                        ),
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer
                    ),
                    onClick = {
                        viewModel.deleteAccount().also {
                            navHostController.navigate(
                                Screens.SignInScreen.route
                            ) {
                                popUpTo(Screens.HomeScreen.route) {
                                    inclusive = true
                                }
                            }
                        }
                    },
                ) {
                    ListItem(
                        leadingContent = {
                            Icon(
                                imageVector = Icons.Outlined.PersonRemoveAlt1,
                                contentDescription = null
                            )
                        },
                        headlineContent = { Text(text = stringResource(R.string.delete_account)) },
                        colors = ListItemDefaults.colors(
                            leadingIconColor = MaterialTheme.colorScheme.onErrorContainer,
                            headlineColor = MaterialTheme.colorScheme.onErrorContainer,
                            containerColor = Color.Transparent,
                        ),
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            if (showLogOutDialog) CustomDialogBox(
                icon = { Icon(imageVector = Icons.Outlined.Logout, contentDescription = null) },
                title = "Log Out",
                text = "You are also logged out from your personal cloud data.",
                confirmButtonText = "Log Out",
                dismissButtonText = "Cancel",
                onConfirmClick = {
                    viewModel.logOut().also {
                        navHostController.navigate(
                            Screens.SignInScreen.route
                        ) {
                            popUpTo(Screens.HomeScreen.route) {
                                inclusive = true
                            }
                        }
                    }
                },
                onDismissClick = { showLogOutDialog = !showLogOutDialog },
            )


        }
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(
        navHostController = rememberNavController(),
        viewModel = viewModel<HomeScreenViewModel>(),
    )
}