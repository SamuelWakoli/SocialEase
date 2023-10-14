package com.samwrotethecode.socialease.ui.presentation.home.appbar_destinations

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.samwrotethecode.socialease.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navHostController: NavHostController
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(id = R.string.search)) },
                navigationIcon = {
                    IconButton(onClick = {
                        // TODO: Navigate back, then save settings
                        navHostController.navigateUp()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(id = R.string.navigate_back)
                        )
                    }

                },
            )
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {

        }
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(
        navHostController = rememberNavController(),
    )
}