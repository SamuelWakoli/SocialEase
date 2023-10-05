package com.samwrotethecode.socialease.ui.presentation.home.home_screen_composables

import android.widget.GridLayout
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.samwrotethecode.socialease.R

@Composable
fun HomeScreenBody(paddingValues: PaddingValues, navHostController: NavHostController) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
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