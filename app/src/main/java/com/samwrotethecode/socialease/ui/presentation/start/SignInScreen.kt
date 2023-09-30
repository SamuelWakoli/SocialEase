package com.samwrotethecode.socialease.ui.presentation.start

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SignInScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .width(600.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(16.dp))
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
        Spacer(modifier = Modifier.height(16.dp))
        ScrollableTabRow(selectedTabIndex = 0) {
            
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SignInScreenPreview() {
    SignInScreen()
}

@Preview(
    device = "spec:parent=pixel_5,orientation=landscape", showSystemUi = true, showBackground = true
)
@Composable
fun SignInScreenPreviewRotated() {
    SignInScreen()
}