package com.samwrotethecode.socialease.ui.presentation.home.home_screen_composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeScreenBody(paddingValues: PaddingValues, navHostController: NavHostController) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
//       LazyVerticalGrid(columns = , content = )
        for (item in homeScreenBodyData) {
            HomeScreenBodyItem(
                navHostController = rememberNavController(), HomeBodyItemModel(
                    imageDrawable = item.imageDrawable,
                    title = item.title,
                    description = item.description,
                    navigationRoute = item.navigationRoute,
                )
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenBodyPreview() {
    HomeScreenBody(
        paddingValues = PaddingValues(),
        navHostController = rememberNavController(),
    )
}