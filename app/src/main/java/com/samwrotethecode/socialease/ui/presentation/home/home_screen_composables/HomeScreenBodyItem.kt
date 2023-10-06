package com.samwrotethecode.socialease.ui.presentation.home.home_screen_composables

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun HomeScreenBodyItem(navHostController: NavHostController, homeBodyItemModel: HomeBodyItemModel) {
    Card(
        modifier = Modifier
            .widthIn(min = 200.dp, max = 500.dp)
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
                    maxLines = 4,
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
                navHostController.navigate(homeBodyItemModel.navigationRoute)
            })
    }
}

@Preview(
    showSystemUi = true, showBackground = true,
)
@Composable
fun HomeScreenBodyItemPreview() {
    HomeScreenBodyItem(
        navHostController = rememberNavController(), HomeBodyItemModel(
            imageDrawable = R.drawable.intro_img_1,
            title = R.string.title_1,
            description = R.string.lorem_ipsum,
            navigationRoute = "",
        )
    )
}

data class HomeBodyItemModel(
    @DrawableRes val imageDrawable: Int,
    @StringRes val title: Int,
    @StringRes val description: Int,
    val navigationRoute: String,
)

val homeScreenBodyData = listOf<HomeBodyItemModel>(
    // TODO: Add home data here
    HomeBodyItemModel(
        imageDrawable = R.drawable.intro_img_1,
        title = R.string.title_1,
        description = R.string.lorem_ipsum,
        navigationRoute = "",
    ),

    HomeBodyItemModel(
        imageDrawable = R.drawable.intro_img_2,
        title = R.string.title_2,
        description = R.string.lorem_ipsum,
        navigationRoute = "",
    ),

    HomeBodyItemModel(
        imageDrawable = R.drawable.intro_img_3,
        title = R.string.title_3,
        description = R.string.lorem_ipsum,
        navigationRoute = "",
    ),

    HomeBodyItemModel(
        imageDrawable = R.drawable.intro_img_4,
        title = R.string.title_4,
        description = R.string.lorem_ipsum,
        navigationRoute = "",
    ),
//    HomeBodyItemModel(
//        imageDrawable = R.drawable.intro_img_1,
//        title = R.string.title_1,
//        description = R.string.lorem_ipsum,
//        navigationRoute = "",
//    ),
//
//    HomeBodyItemModel(
//        imageDrawable = R.drawable.intro_img_2,
//        title = R.string.title_2,
//        description = R.string.lorem_ipsum,
//        navigationRoute = "",
//    ),
//
//    HomeBodyItemModel(
//        imageDrawable = R.drawable.intro_img_3,
//        title = R.string.title_3,
//        description = R.string.lorem_ipsum,
//        navigationRoute = "",
//    ),
//
//    HomeBodyItemModel(
//        imageDrawable = R.drawable.intro_img_4,
//        title = R.string.title_4,
//        description = R.string.lorem_ipsum,
//        navigationRoute = "",
//    ),


)