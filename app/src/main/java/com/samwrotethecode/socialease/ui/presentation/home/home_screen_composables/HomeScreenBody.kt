package com.samwrotethecode.socialease.ui.presentation.home.home_screen_composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.samwrotethecode.socialease.ui.presentation.home.viewmodels.HomeBodyItemModel
import com.samwrotethecode.socialease.ui.presentation.home.viewmodels.homeScreenBodyData

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreenBody(
    paddingValues: PaddingValues,
    navHostController: NavHostController,
    windowSize: WindowWidthSizeClass,
) {
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
                WindowWidthSizeClass.Compact -> homeScreenBodyData.size
                WindowWidthSizeClass.Medium -> homeScreenBodyData.size / 2 + 1
                WindowWidthSizeClass.Expanded -> homeScreenBodyData.size / 3 + 1
                else -> homeScreenBodyData.size
            }, // added + 1 to fit odd numbers

        ) {
            for (item in homeScreenBodyData) {
                HomeScreenBodyItem(
                    windowSize = windowSize,
                    navHostController = rememberNavController(),
                    homeBodyItemModel = HomeBodyItemModel(
                        imageDrawable = item.imageDrawable,
                        title = item.title,
                        description = item.description,
                        topicCategory = item.topicCategory,
                    ),
                )
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
    )
}

@Preview(
    device = "spec:parent=pixel_5,orientation=landscape", showSystemUi = true,
    showBackground = true
)
@Composable
fun HomeScreenBodyPreviewRorated() {
    HomeScreenBody(
        paddingValues = PaddingValues(),
        navHostController = rememberNavController(),
        windowSize = WindowWidthSizeClass.Medium,
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
    )
}