package com.samwrotethecode.socialease.ui.presentation.start

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Email
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.samwrotethecode.socialease.R
import com.samwrotethecode.socialease.ui.presentation.composables.CustomDialogBox
import com.samwrotethecode.socialease.ui.presentation.start.viewmodels.SignInScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(
    navHostController: NavHostController,
    viewModel: SignInScreenViewModel,
) {
    val uiState = viewModel.uiState.collectAsState().value

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.intro_img_1),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            alpha = 0.1f
        )
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(text = "Forgot Password") },
                    navigationIcon = {
                        IconButton(onClick = { navHostController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.Outlined.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.Transparent,
                    )
                )
            },
            containerColor = Color.Transparent
        ) { padding ->

            Column(
                Modifier
                    .padding(
                        padding
                    )
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
            ) {
                Text(
                    "Enter your email to get password reset link",
                    modifier = Modifier.padding(8.dp),
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = uiState.email,
                    onValueChange = { viewModel.updateEmail(it) },
                    modifier = Modifier
                        .width(600.dp)
                        .padding(8.dp),
                    singleLine = true,
                    isError = uiState.showEmailError,
                    supportingText = { if (uiState.showEmailError) Text(text = "Email cannot be empty") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Email, contentDescription = null
                        )
                    },
                    label = { Text(text = "Email") },
                    trailingIcon = {
                        if (uiState.isSignInButtonLoading) CircularProgressIndicator(
                            strokeWidth = 2.dp,
                            modifier = Modifier.size(20.dp),
                        )
                        else IconButton(onClick = { viewModel.sendPwdResetLink() }) {
                            Icon(
                                imageVector = Icons.Outlined.Send,
                                contentDescription = "send"
                            )
                        }
                    },
                    shape = MaterialTheme.shapes.medium,
                    keyboardOptions = KeyboardOptions().copy(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Email,
                        autoCorrect = false,
                        capitalization = KeyboardCapitalization.None,
                    ),
                )
                if (!uiState.errorMessage.isNullOrEmpty()) Text(
                    text = uiState.errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(8.dp),
                )

                if (uiState.showDialogPwdResetEmailSent) {
                    CustomDialogBox(
                        icon = {
                            Icon(imageVector = Icons.Outlined.Email, contentDescription = null)
                        },
                        title = "Email Sent",
                        text = "An email containing password reset link has been sent to your email address.",
                        confirmButtonText = "Okay",
                        dismissButtonText = null,
                        onConfirmClick = {
                            navHostController.navigateUp()
                        },
                        onDismissClick = { },
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun ForgotPasswordScreenPreview() {
    ForgotPasswordScreen(
        navHostController = rememberNavController(),
        viewModel = viewModel<SignInScreenViewModel>(),
    )
}
