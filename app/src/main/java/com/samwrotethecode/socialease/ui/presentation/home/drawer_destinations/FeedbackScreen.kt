package com.samwrotethecode.socialease.ui.presentation.home.drawer_destinations

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.samwrotethecode.socialease.R
import com.samwrotethecode.socialease.ui.presentation.composables.CustomDialogBox
import com.samwrotethecode.socialease.ui.presentation.home.viewmodels.FeedbackScreenViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun FeedbackScreen(
    navHostController: NavHostController,
    viewModel: FeedbackScreenViewModel
) {
    val uiState = viewModel.uiState.collectAsState().value
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(key1 = Unit, block = {
        focusRequester.requestFocus()
    })

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text(text = stringResource(id = R.string.feedback)) },
            navigationIcon = {
                IconButton(onClick = { navHostController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.navigate_back),
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
                } else IconButton(onClick = {
                    keyboardController?.hide()
                    viewModel.sendFeedback()
                }) {
                    Icon(
                        imageVector = Icons.Outlined.Send,
                        contentDescription = stringResource(id = R.string.send)
                    )
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                actionIconContentColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.primary,
                navigationIconContentColor = MaterialTheme.colorScheme.primary,
            )
        )
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            OutlinedTextField(
                value = uiState.text,
                onValueChange = {
                    viewModel.updateFeedbackText(it)
                },
                shape = MaterialTheme.shapes.large,
                modifier = Modifier
                    .widthIn(max = 600.dp)
                    .padding(8.dp)
                    .focusRequester(focusRequester = focusRequester)
                    .onFocusChanged {
                        if (it.isFocused) keyboardController?.show()
                        else keyboardController?.hide()
                    },
                placeholder = {
                    Text(text = stringResource(R.string.type_your_feedback_here))
                },
                supportingText = {
                    if (uiState.showError) Text(text = stringResource(id = R.string.feedback_text_cannot_be_empty))
                },
                isError = uiState.showError,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    errorContainerColor = Color.Transparent,
                ),
                keyboardActions = KeyboardActions(
                    onSend = {
                        keyboardController?.hide()
                        viewModel.sendFeedback()
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    autoCorrect = true,
                    imeAction = ImeAction.Send,
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = uiState.errorMessage)
            Spacer(modifier = Modifier.height(400.dp))
        }

        if (uiState.feedbackSent) {
            CustomDialogBox(
                icon = { Icon(imageVector = Icons.Default.Send, contentDescription = null) },
                title = stringResource(R.string.feedback_sent),
                text = stringResource(R.string.the_app_developer_has_received_your_feedback_and_will_take_time_to_review_it_thank_you),
                confirmButtonText = stringResource(id = R.string.okay),
                dismissButtonText = "",
                onConfirmClick = {
                    viewModel.resetState()
                    navHostController.navigateUp()
                },
                onDismissClick = {
                    viewModel.resetState()
                    navHostController.navigateUp()
                },
            )
        }
    }
}

@Preview
@Composable
fun FeedbackScreenPreview() {
    FeedbackScreen(
        navHostController = rememberNavController(),
        viewModel = viewModel<FeedbackScreenViewModel>(),
    )
}