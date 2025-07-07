package com.samwrotethecode.socialease.ui.presentation.start

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.ContentType
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentType
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.samwrotethecode.socialease.R
import com.samwrotethecode.socialease.ui.presentation.composables.GoogleSignInButton
import com.samwrotethecode.socialease.ui.presentation.home.viewmodels.SignInScreenViewModel
import com.samwrotethecode.socialease.ui.presentation.navigation.Screens

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignInScreen(
    navHostController: NavHostController,
    viewModel: SignInScreenViewModel,
    onSignInWithGoogle: () -> Unit
) {

    val uiState = viewModel.uiState.collectAsState().value

    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    LaunchedEffect(key1 = uiState.isSignInSuccess, block = {
        if (uiState.isSignInSuccess) {
            Toast.makeText(
                context, R.string.signed_in_successfully, Toast.LENGTH_LONG
            ).show()

            navHostController.navigate(Screens.HomeScreen.route) {
                launchSingleTop = true
                navHostController.popBackStack()
            }
        }
    })

    Box(
        contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
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
                .fillMaxSize(),
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
                            text = stringResource(R.string.sign_in),
                            style = TextStyle(
                                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                                fontWeight = MaterialTheme.typography.headlineSmall.fontWeight,
                            ),
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = uiState.email,
                        onValueChange = { viewModel.updateEmail(it) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .semantics { contentType = ContentType.EmailAddress },
                        singleLine = true,
                        isError = uiState.showEmailError,
                        supportingText = { if (uiState.showEmailError) Text(text = stringResource(R.string.email_cannot_be_empty)) },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Email, contentDescription = null
                            )
                        },
                        label = { Text(text = stringResource(R.string.email)) },
                        shape = MaterialTheme.shapes.medium,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Email,
                            autoCorrectEnabled = false,
                            capitalization = KeyboardCapitalization.None,
                        ),
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = uiState.password,
                        onValueChange = { viewModel.updatePassword(it) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .semantics {
                                contentType = ContentType.Password
                            },
                        singleLine = true,
                        isError = uiState.showPasswordError,
                        supportingText = {
                            if (uiState.showPasswordError) Text(
                                text = stringResource(
                                    R.string.password_cannot_be_empty
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
                        label = { Text(text = stringResource(R.string.password)) },
                        trailingIcon = {
                            IconButton(onClick = {
                                viewModel.hideOrShowPassword()
                            }) {
                                Icon(
                                    imageVector = if (uiState.showPassword) Icons.Outlined.VisibilityOff
                                    else Icons.Outlined.Visibility,
                                    contentDescription = if (uiState.showPassword) stringResource(R.string.hide_password) else stringResource(
                                        R.string.show
                                    )
                                )
                            }
                        },
                        shape = MaterialTheme.shapes.medium,
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrectEnabled = false,
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(onDone = {
                            // first hide keyboard
                            keyboardController?.hide()
                            // then sign in
                            viewModel.signInWithEmailPwd()
                        }),
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = stringResource(R.string.forgot_password), style = TextStyle(
                        color = MaterialTheme.colorScheme.secondary,
                        textDecoration = TextDecoration.Underline,
                    ), modifier = Modifier
                            .clickable {
                                navHostController.navigate(Screens.ForgotPasswordScreen.route)
                            }
                            .align(alignment = Alignment.End)
                            .padding(horizontal = 16.dp))
                    Spacer(modifier = Modifier.height(12.dp))
                    if (uiState.errorMessage != null) Text(
                        text = uiState.errorMessage, color = MaterialTheme.colorScheme.error
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            onClick = {
                                viewModel.signInWithEmailPwd()
                            },
                        ) {
                            Row {
                                Text(
                                    text = stringResource(id = R.string.sign_in),
                                    modifier = Modifier.padding(horizontal = 24.dp)
                                )
                                if (uiState.isSignInButtonLoading) {
                                    Spacer(modifier = Modifier.width(12.dp))
                                    CircularProgressIndicator(
                                        modifier = Modifier.size(20.dp),
                                        color = MaterialTheme.colorScheme.onPrimary,
                                        strokeWidth = 2.dp,
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        OutlinedButton(
                            onClick = {
                                navHostController.navigate(Screens.RegisterScreen.route) {
                                    launchSingleTop = true
                                }
                            },
                        ) {
                            Row {
                                Text(text = stringResource(R.string.create_account))
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    GoogleSignInButton(
                        modifier = Modifier
                            .padding(8.dp)
                            .width(600.dp),
                        onClick = { onSignInWithGoogle() })
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SignInScreenPreview() {
    SignInScreen(
        navHostController = rememberNavController(),
        viewModel = viewModel<SignInScreenViewModel>(),
    ) {}
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(
    device = "spec:parent=pixel_5,orientation=landscape", showSystemUi = true, showBackground = true
)
@Composable
fun SignInScreenPreviewRotated() {
    SignInScreen(
        navHostController = rememberNavController(),
        viewModel = viewModel<SignInScreenViewModel>(),
    ) {}
}