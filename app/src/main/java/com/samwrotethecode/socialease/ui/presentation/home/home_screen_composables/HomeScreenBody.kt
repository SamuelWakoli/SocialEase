package com.samwrotethecode.socialease.ui.presentation.home.home_screen_composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.samwrotethecode.socialease.ui.presentation.home.viewmodels.HomeBodyItemModel
import com.samwrotethecode.socialease.ui.presentation.home.viewmodels.HomeScreenViewModel
import com.samwrotethecode.socialease.ui.presentation.home.viewmodels.homeScreenBodyData

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreenBody(
    paddingValues: PaddingValues,
    navHostController: NavHostController,
    windowSize: WindowWidthSizeClass,
    viewModel: HomeScreenViewModel,
) {
    if (windowSize == WindowWidthSizeClass.Compact) {
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(count = homeScreenBodyData.size) { index ->
                HomeScreenBodyItem(
                    windowSize = windowSize,
                    navHostController = navHostController,
                    homeBodyItemModel = HomeBodyItemModel(
                        imageDrawable = homeScreenBodyData[index].imageDrawable,
                        title = homeScreenBodyData[index].title,
                        description = homeScreenBodyData[index].description,
                        topicCategory = homeScreenBodyData[index].topicCategory,
                    ),
                    viewModel = viewModel,
                )
            }
        }
    } else {
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            FlowColumn(
                verticalArrangement = Arrangement.Top,
                horizontalArrangement = Arrangement.Center,
                maxItemsInEachColumn =
                when (windowSize) {
                    WindowWidthSizeClass.Medium -> homeScreenBodyData.size / 2 + 1
                    WindowWidthSizeClass.Expanded -> homeScreenBodyData.size / 4 + 1
                    else -> homeScreenBodyData.size
                }, // added + 1 to fit odd numbers

            ) {
                for (item in homeScreenBodyData) {
                    HomeScreenBodyItem(
                        windowSize = windowSize,
                        navHostController = navHostController,
                        homeBodyItemModel = HomeBodyItemModel(
                            imageDrawable = item.imageDrawable,
                            title = item.title,
                            description = item.description,
                            topicCategory = item.topicCategory,
                        ),
                        viewModel = viewModel,
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomeScreenBodyPreview() {
    HomeScreenBody(
        paddingValues = PaddingValues(),
        navHostController = rememberNavController(),
        windowSize = WindowWidthSizeClass.Compact,
        viewModel = viewModel<HomeScreenViewModel>(),
    )
}

@Preview(
    device = "spec:parent=pixel_5,orientation=landscape", showSystemUi = true,
    showBackground = true
)
@Composable
fun HomeScreenBodyPreviewRotated() {
    HomeScreenBody(
        paddingValues = PaddingValues(),
        navHostController = rememberNavController(),
        windowSize = WindowWidthSizeClass.Medium,
        viewModel = viewModel<HomeScreenViewModel>(),
    )
}

@Preview(
    device = "spec:width=1280dp,height=800dp,dpi=240,orientation=portrait",
    showSystemUi = true, showBackground = true
)
@Composable
fun HomeScreenBodyTabletPreview() {
    HomeScreenBody(
        paddingValues = PaddingValues(),
        navHostController = rememberNavController(),
        windowSize = WindowWidthSizeClass.Medium,
        viewModel = viewModel<HomeScreenViewModel>(),
    )
}

@Preview(
    device = "spec:width=1280dp,height=800dp,dpi=240",
    showSystemUi = true, showBackground = true
)
@Composable
fun HomeScreenBodyTabletPreviewRotated() {
    HomeScreenBody(
        paddingValues = PaddingValues(),
        navHostController = rememberNavController(),
        windowSize = WindowWidthSizeClass.Expanded,
        viewModel = viewModel<HomeScreenViewModel>(),
    )
}