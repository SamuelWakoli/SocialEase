package com.samwrotethecode.socialease.ui.presentation.home.home_screen_composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.samwrotethecode.socialease.R
import com.samwrotethecode.socialease.ui.presentation.home.viewmodels.HomeBodyItemModel
import com.samwrotethecode.socialease.ui.presentation.home.viewmodels.TopicCategories

@Composable
fun HomeScreenBodyItem(
    windowSize: WindowWidthSizeClass,
    navHostController: NavHostController,
    homeBodyItemModel: HomeBodyItemModel
) {

    Card(
        modifier = Modifier
            .width(
                when (windowSize) {
                    WindowWidthSizeClass.Compact -> 400.dp
                    WindowWidthSizeClass.Medium -> 300.dp
                    else -> 290.dp
                }
            )
            .padding(horizontal = 8.dp, vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ),
        shape = MaterialTheme.shapes.large,
    ) {
        Image(
            painter = painterResource(id = homeBodyItemModel.imageDrawable),
            contentDescription = null,
            modifier = Modifier
                .heightIn(min = 200.dp, max = 250.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop,
        )
        ListItem(headlineContent = { Text(text = stringResource(id = homeBodyItemModel.title)) },
            supportingContent = {
                Text(
                    text = stringResource(id = homeBodyItemModel.description),
//                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis,
                )
            },
            colors = ListItemDefaults.colors(
                headlineColor = MaterialTheme.colorScheme.onSecondaryContainer,
                supportingColor = MaterialTheme.colorScheme.onSecondaryContainer,
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            trailingContent = {
                Icon(
                    imageVector = Icons.Default.ArrowForwardIos,
                    contentDescription = null,
                )
            },
            modifier = Modifier.clickable {

            })
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Preview(
    showSystemUi = true, showBackground = true,
)
@Composable
fun HomeScreenBodyItemPreview() {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        FlowColumn(
            verticalArrangement = Arrangement.Top,
            maxItemsInEachColumn = 10,
        ) {

            repeat(10) {
                HomeScreenBodyItem(
                    windowSize = WindowWidthSizeClass.Compact,
                    navHostController = rememberNavController(),
                    homeBodyItemModel = HomeBodyItemModel(
                        imageDrawable = R.drawable.communication,
                        title = R.string.communication,
                        description = R.string.communication_description,
                        topicCategory = TopicCategories.COMMUNICATION,
                    ),
                )
            }
        }

    }
}

@OptIn(ExperimentalLayoutApi::class)
@Preview(
    showSystemUi = true, showBackground = true,
    device = "spec:parent=pixel_5,orientation=landscape",
)
@Composable
fun HomeScreenBodyItemPreviewMedium() {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        FlowColumn(
            verticalArrangement = Arrangement.Top,
            maxItemsInEachColumn = 5,
        ) {

            repeat(10) {
                HomeScreenBodyItem(
                    windowSize = WindowWidthSizeClass.Compact,
                    navHostController = rememberNavController(),
                    homeBodyItemModel = HomeBodyItemModel(
                        imageDrawable = R.drawable.communication,
                        title = R.string.communication,
                        description = R.string.communication_description,
                        topicCategory = TopicCategories.COMMUNICATION,
                    ),
                )
            }
        }

    }
}

@OptIn(ExperimentalLayoutApi::class)
@Preview(
    showSystemUi = true, showBackground = true,
    device = "spec:width=1280dp,height=800dp,dpi=240",
)
@Composable
fun HomeScreenBodyItemPreviewExpanded() {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        FlowColumn(
            verticalArrangement = Arrangement.Top,
            maxItemsInEachColumn = 10 / 3 + 1,
//            maxItemsInEachColumn = Total / Columns + 1(for odd num) ,

        ) {

            repeat(10) {
                HomeScreenBodyItem(
                    windowSize = WindowWidthSizeClass.Compact,
                    navHostController = rememberNavController(),
                    homeBodyItemModel = HomeBodyItemModel(
                        imageDrawable = R.drawable.communication,
                        title = R.string.communication,
                        description = R.string.communication_description,
                        topicCategory = TopicCategories.COMMUNICATION,
                    ),
                )
            }
        }

    }
}