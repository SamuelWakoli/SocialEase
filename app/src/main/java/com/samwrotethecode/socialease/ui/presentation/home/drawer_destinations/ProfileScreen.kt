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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.samwrotethecode.socialease.ui.presentation.composables.CoilImage
import com.samwrotethecode.socialease.ui.presentation.home.viewmodels.HomeScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navHostController: NavHostController) {
    val viewModel = viewModel<HomeScreenViewModel>()
    val uiState = viewModel.uiState.collectAsState().value

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Profile")
                },
                navigationIcon = {
                    IconButton(onClick = { navHostController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Navigate back"
                        )
                    }
                },
            )
        }
    ) {
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
                        photoUrl = uiState.photoUrl,
                        size = 160.dp
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                ListItem(
                    headlineContent = { Text(text = uiState.displayName.toString()) },
                    supportingContent = { Text(text = uiState.email.toString()) },
                )
                Spacer(modifier = Modifier.height(8.dp))
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer
                    )
                ) {
                    ListItem(
                        leadingContent = {
                            Icon(
                                imageVector = Icons.Outlined.Logout,
                                contentDescription = null
                            )
                        },
                        headlineContent = { Text(text = "Log out") },
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
                    )
                ) {
                    ListItem(
                        leadingContent = {
                            Icon(
                                imageVector = Icons.Outlined.PersonRemoveAlt1,
                                contentDescription = null
                            )
                        },
                        headlineContent = { Text(text = "Delete account") },
                        colors = ListItemDefaults.colors(
                            leadingIconColor = MaterialTheme.colorScheme.onErrorContainer,
                            headlineColor = MaterialTheme.colorScheme.onErrorContainer,
                            containerColor = Color.Transparent,
                        ),
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(
        navHostController = rememberNavController(),
    )
}