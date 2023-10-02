package com.samwrotethecode.socialease.ui.presentation.home.home_screen_composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Feedback
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.samwrotethecode.socialease.ui.presentation.composables.CoilImage
import com.samwrotethecode.socialease.ui.presentation.home.viewmodels.HomeScreenViewModel
import com.samwrotethecode.socialease.ui.presentation.home.viewmodels.HomeUiStateModel
import com.samwrotethecode.socialease.ui.presentation.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenDrawer(
    navHostController: NavHostController, uiState: HomeUiStateModel
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
            .width(300.dp)
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        Box(
            modifier = Modifier.size(300.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "SocialEase", style = TextStyle(
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.Monospace,
                    shadow = Shadow(
                        color = MaterialTheme.colorScheme.secondary,
                        offset = Offset(2f, 3f),
                        blurRadius = 26f
                    )
                )
            )

        }
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            modifier = Modifier.padding(horizontal = 16.dp),
            onClick = {
                navHostController.navigate(Screens.ProfileScreen.route) {
                    launchSingleTop = true
                }
            },
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            ListItem(
                headlineContent = {
                    Text(text = uiState.displayName.toString())
                },
                supportingContent = {
                    Text(text = uiState.email.toString())
                },
                leadingContent = {
                    CoilImage(photoUrl = uiState.photoUrl)
                },
            )
        }
        Divider(modifier = Modifier.padding(horizontal = 32.dp, vertical = 8.dp))
        Card(
            modifier = Modifier.padding(horizontal = 16.dp),
            onClick = {},
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            ListItem(
                headlineContent = {
                    Text(
                        text = "Feedback",
                        style = TextStyle(
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize
                        ),
                    )
                },
                leadingContent = {
                    Icon(imageVector = Icons.Outlined.Feedback, contentDescription = null)
                },
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier.padding(horizontal = 16.dp),
            onClick = {},
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            ListItem(
                headlineContent = {
                    Text(
                        text = "About",
                        style = TextStyle(
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize
                        ),
                    )
                },
                leadingContent = {
                    Icon(imageVector = Icons.Outlined.Info, contentDescription = null)
                },
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier.padding(horizontal = 16.dp),
            onClick = {},
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            ListItem(
                headlineContent = {
                    Text(
                        text = "Theme",
                        style = TextStyle(
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize
                        ),
                    )
                },
                leadingContent = {
                    Icon(imageVector = Icons.Outlined.WbSunny, contentDescription = null)
                },
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Preview
@Composable
fun HomeScreenDrawerPreview() {
    HomeScreenDrawer(
        navHostController = rememberNavController(),
        uiState = viewModel<HomeScreenViewModel>().uiState.collectAsState().value,
    )
}

