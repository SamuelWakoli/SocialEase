package com.samwrotethecode.socialease

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.auth.auth
import com.google.firebase.Firebase
import com.samwrotethecode.socialease.ui.presentation.home.viewmodels.SignInScreenViewModel
import com.samwrotethecode.socialease.ui.presentation.navigation.NavGraph
import com.samwrotethecode.socialease.ui.presentation.navigation.Screens
import com.samwrotethecode.socialease.ui.presentation.start.GoogleAuthUiClient
import com.samwrotethecode.socialease.ui.theme.SocialEaseTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val currentUser = Firebase.auth.currentUser
        enableEdgeToEdge()
        setContent {
            SocialEaseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // handles Google Sign in
                    val signInScreenViewModel = viewModel<SignInScreenViewModel>()
                    val launcher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.StartIntentSenderForResult(),
                        onResult = {
                            if (it.resultCode == RESULT_OK) {
                                lifecycleScope.launch {
                                    val signInResult = googleAuthUiClient.signInByIntent(
                                        intent = it.data ?: return@launch
                                    )
                                    signInScreenViewModel.onSignInResult(signInResult)
                                }
                            }
                        }
                    )

                    val windowSize = calculateWindowSizeClass(this)
                    NavGraph(
                        navHostController = rememberNavController(),
                        startDestination =
                            if (currentUser == null) Screens.IntroScreen.route
                            else Screens.HomeScreen.route,
                        windowSize = windowSize.widthSizeClass,
                        signInScreenViewModel = signInScreenViewModel,
                    ) {
                        // now Sign in with Google
                        lifecycleScope.launch {
                            val signInIntentSender = googleAuthUiClient.signIn()
                            launcher.launch(
                                IntentSenderRequest.Builder(
                                    signInIntentSender ?: return@launch
                                ).build()
                            )
                        }
                    }
                }
            }
        }
    }
}