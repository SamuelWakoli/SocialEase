package com.samwrotethecode.socialease

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.samwrotethecode.socialease.ui.presentation.navigation.NavGraph
import com.samwrotethecode.socialease.ui.presentation.navigation.Screens
import com.samwrotethecode.socialease.ui.theme.SocialEaseTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SocialEaseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val windowSize = calculateWindowSizeClass(this)
                    NavGraph(
                        navHostController = rememberNavController(),
                        startDestination = Screens.IntroScreen.route,
                        windowSize = windowSize.widthSizeClass,
                    )
                }
            }
        }
    }
}