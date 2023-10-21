package com.samwrotethecode.socialease.ui.presentation.start

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.samwrotethecode.socialease.R
import com.samwrotethecode.socialease.ui.presentation.composables.GoogleSignInButton
import com.samwrotethecode.socialease.ui.presentation.home.viewmodels.SignInScreenViewModel
import com.samwrotethecode.socialease.ui.presentation.navigation.Screens

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    windowSize: WindowWidthSizeClass,
    navHostController: NavHostController,
    viewModel: SignInScreenViewModel,
    onSignInWithGoogle: () -> Unit
) {

    val uiState = viewModel.uiState.collectAsState().value

    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    LaunchedEffect(key1 = uiState.isSignInSuccess, block = {
        if (uiState.isSignInSuccess) {
            Toast.makeText(context, R.string.signed_in_successfully, Toast.LENGTH_LONG).show()

            navHostController.navigate(Screens.HomeScreen.route) {
                popUpTo(Screens.SignInScreen.route) {
                    inclusive = true
                }
            }
        }
    })

    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            CenterAlignedTopAppBar(
                title = { }, navigationIcon = {
                    IconButton(onClick = { navHostController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(id = R.string.navigate_back),
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = Color.Transparent,
                )
            )
        }
    ) { paddingValues ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.intro_img_1),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                alpha = 0.1f,
            )
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier.width(600.dp),
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(16.dp),

                        ) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = stringResource(id = R.string.app_name), style = TextStyle(
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
                        Spacer(modifier = Modifier.height(32.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = stringResource(id = R.string.create_account),
                                style = TextStyle(
                                    fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                                    fontWeight = MaterialTheme.typography.headlineSmall.fontWeight,
                                ),
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = uiState.name,
                            onValueChange = { viewModel.updateName(it) },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            isError = uiState.showNameError,
                            supportingText = {
                                if (uiState.showEmailError) Text(
                                    text = stringResource(
                                        R.string.name_cannot_be_empty
                                    )
                                )
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.AccountCircle,
                                    contentDescription = null
                                )
                            },
                            label = { Text(text = stringResource(R.string.name)) },
                            shape = MaterialTheme.shapes.medium,
                            keyboardOptions = KeyboardOptions().copy(
                                imeAction = ImeAction.Next,
                                keyboardType = KeyboardType.Text,
                                autoCorrect = false,
                                capitalization = KeyboardCapitalization.None,
                            ),
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = uiState.email,
                            onValueChange = { viewModel.updateEmail(it) },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            isError = uiState.showEmailError,
                            supportingText = {
                                if (uiState.showEmailError) Text(
                                    text = stringResource(
                                        id = R.string.email_cannot_be_empty
                                    )
                                )
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Email, contentDescription = null
                                )
                            },
                            label = { Text(text = stringResource(id = R.string.email)) },
                            shape = MaterialTheme.shapes.medium,
                            keyboardOptions = KeyboardOptions().copy(
                                imeAction = ImeAction.Next,
                                keyboardType = KeyboardType.Email,
                                autoCorrect = false,
                                capitalization = KeyboardCapitalization.None,
                            ),
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = uiState.password,
                            onValueChange = { viewModel.updatePassword(it) },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            isError = uiState.showPasswordError,
                            supportingText = {
                                if (uiState.showPasswordError) Text(
                                    text = stringResource(
                                        id = R.string.password_cannot_be_empty
                                    )
                                )
                            },
                            visualTransformation = if (uiState.showPassword) VisualTransformation.None
                            else PasswordVisualTransformation(),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Lock, contentDescription = null
                                )
                            },
                            label = { Text(text = stringResource(id = R.string.password)) },
                            trailingIcon = {
                                IconButton(onClick = {
                                    viewModel.hideOrShowPassword()
                                }) {
                                    Icon(
                                        imageVector = if (uiState.showPassword) Icons.Outlined.VisibilityOff
                                        else Icons.Outlined.Visibility,
                                        contentDescription = if (uiState.showPassword) stringResource(
                                            id = R.string.hide_password
                                        ) else stringResource(id = R.string.show)
                                    )
                                }
                            },
                            shape = MaterialTheme.shapes.medium,
                            keyboardOptions = KeyboardOptions(
                                autoCorrect = false,
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Done,
                                capitalization = KeyboardCapitalization.None
                            ),
                            keyboardActions = KeyboardActions(onDone = {
                                // first hide keyboard
                                keyboardController?.hide()
                                // then sign in
                                viewModel.registerWithEmailPwd()
                            }),
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        if (uiState.errorMessage != null) Text(
                            text = uiState.errorMessage, color = MaterialTheme.colorScheme.error
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        OutlinedButton(
                            onClick = {
                                viewModel.registerWithEmailPwd()
                            }, modifier = Modifier.fillMaxWidth()
                        ) {
                            Row {
                                Text(text = stringResource(id = R.string.create_account))
                                if (uiState.isCreateAccountButtonLoading) {
                                    Spacer(modifier = Modifier.width(12.dp))
                                    CircularProgressIndicator(
                                        modifier = Modifier.size(20.dp),
                                        color = MaterialTheme.colorScheme.primary,
                                        strokeWidth = 2.dp,
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        GoogleSignInButton(modifier = Modifier
                            .padding(8.dp)
                            .width(600.dp),
                            onClick = { onSignInWithGoogle() })
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(
        windowSize = calculateWindowSizeClass(
            LocalContext.current as Activity
        ).widthSizeClass,
        navHostController = rememberNavController(),
        viewModel = viewModel<SignInScreenViewModel>(),
    ) {}
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(
    device = "spec:parent=pixel_5,orientation=landscape", showSystemUi = true, showBackground = true
)
@Composable
fun RegisterScreenPreviewRotated() {
    RegisterScreen(
        windowSize = calculateWindowSizeClass(
            LocalContext.current as Activity
        ).widthSizeClass,
        navHostController = rememberNavController(),
        viewModel = viewModel<SignInScreenViewModel>(),
    ) {}
}