package com.samwrotethecode.socialease.ui.presentation.home.drawer_destinations

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.samwrotethecode.socialease.ui.presentation.composables.CustomDialogBox
import com.samwrotethecode.socialease.ui.presentation.home.viewmodels.FeedbackScreenViewmodel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedbackScreen(
    navHostController: NavHostController,
) {
    val viewmodel = viewModel<FeedbackScreenViewmodel>()
    val uiState = viewmodel.uiState.collectAsState().value

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text(text = "Feedback") },
            navigationIcon = {
                IconButton(onClick = { navHostController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Navigate back",
                    )
                }
            },
            actions = {
                if (uiState.isSendingFeedback) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(20.dp),
                        strokeWidth = 2.dp,
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                } else IconButton(onClick = { viewmodel.sendFeedback() }) {
                    Icon(imageVector = Icons.Outlined.Send, contentDescription = "Send")
                }
            }
        )
    }) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            TextField(
                value = uiState.text,
                onValueChange = {
                    viewmodel.updateFeedbackText(it)
                },
                modifier = Modifier
                    .height(400.dp),
                placeholder = {
                    Text(text = "Type your feedback here")
                },
                isError = uiState.showError,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
            )
            Spacer(modifier = Modifier.height(400.dp))
        }

        if (uiState.feedbackSent) CustomDialogBox(
            icon = { Icon(imageVector = Icons.Default.Send, contentDescription = null) },
            title = "Feedback Sent",
            text = "The app developer has received your feedback and will take time to review it. Thank you.",
            confirmButtonText = "Okay",
            dismissButtonText = "",
            onConfirmClick = { navHostController.navigateUp() },
            onDismissClick = { navHostController.navigateUp() })
    }
}

@Preview
@Composable
fun FeedbackScreenPreview() {
    FeedbackScreen(navHostController = rememberNavController())
}