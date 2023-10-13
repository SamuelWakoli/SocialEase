package com.samwrotethecode.socialease.ui.presentation.home.drawer_destinations

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.samwrotethecode.socialease.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navHostController: NavHostController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navHostController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(R.string.navigate_back)
                        )
                    }
                },
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            ListItem(
                headlineContent = { },
                supportingContent = {
                    Text(
                        text = stringResource(R.string.about_social_skills)
                    )
                },
            )
            ListItem(
                headlineContent = { Text(text = stringResource(R.string.why_are_social_skills_important)) },
                supportingContent = {
                    Text(
                        text = stringResource(R.string.why_are_social_skills_important_description)
                    )
                },
            )
            ListItem(
                headlineContent = { Text(text = stringResource(R.string.how_to_improve_your_social_skills)) },
                supportingContent = {
                    Text(
                        text = stringResource(R.string.how_to_improve_your_social_skills_description)
                    )
                },
            )

        }
    }
}

@Preview
@Composable
fun AboutScreenPreview() {
    AboutScreen(navHostController = rememberNavController())
}