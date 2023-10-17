package com.samwrotethecode.socialease.ui.presentation.home.content

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BookmarkAdd
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samwrotethecode.socialease.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubTopicListItem(
    title: String,
    generalDescription: String,
    windowSize: WindowWidthSizeClass,
    onClick: () -> Unit = {},
    onClickBookmark: () -> Unit = {},
    onClickShare: () -> Unit = {},

) {
    Card(
        onClick = onClick,
        modifier = Modifier.widthIn(
            max = when (windowSize) {
                WindowWidthSizeClass.Compact -> 400.dp
                WindowWidthSizeClass.Medium -> 300.dp
                else -> 280.dp
            }
        ).padding(horizontal = 8.dp, vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
        )
    ) {
        ListItem(
            headlineContent = {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium
                )
            },
            supportingContent = {
                Column {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(SpanStyle(color = MaterialTheme.colorScheme.onTertiaryContainer)) {
                                append(generalDescription)
                            }
                            append(" ")
                            withStyle(SpanStyle(color = MaterialTheme.colorScheme.secondary)) {
                                append("read more")
                            }
                        },
                        overflow = TextOverflow.Ellipsis,
                    )
                    Row(
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Outlined.BookmarkAdd,
                                contentDescription = "Add to bookmarks",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Outlined.Share,
                                contentDescription = "Share",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SubTopicListItemCompactPreview() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            repeat(10) {
                SubTopicListItem(
                    title = "Title",
                    generalDescription = stringResource(id = R.string.lorem_ipsum),
                    windowSize = WindowWidthSizeClass.Compact,
                )
            }
        }
    }
}

@Preview(
    showBackground = true, showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun SubTopicListItemDarkThemeCompactPreview() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            repeat(10) {
                SubTopicListItem(
                    title = "Title",
                    generalDescription = stringResource(id = R.string.lorem_ipsum),
                    windowSize = WindowWidthSizeClass.Compact
                )
            }
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SubTopicListItemMediumPreview() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            repeat(10) {
                SubTopicListItem(
                    title = "Title",
                    generalDescription = stringResource(id = R.string.lorem_ipsum),
                    windowSize = WindowWidthSizeClass.Medium,
                )
            }
        }
    }
}

@Preview(
    showBackground = true, showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun SubTopicListItemDarkThemeMediumPreview() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            repeat(10) {
                SubTopicListItem(
                    title = "Title",
                    generalDescription = stringResource(id = R.string.lorem_ipsum),
                    windowSize = WindowWidthSizeClass.Medium
                )
            }
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SubTopicListItemExpandedPreview() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            repeat(10) {
                SubTopicListItem(
                    title = "Title",
                    generalDescription = stringResource(id = R.string.lorem_ipsum),
                    windowSize = WindowWidthSizeClass.Expanded,
                )
            }
        }
    }
}

@Preview(
    showBackground = true, showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun SubTopicListItemDarkThemeExpandedPreview() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            repeat(10) {
                SubTopicListItem(
                    title = "Title",
                    generalDescription = stringResource(id = R.string.lorem_ipsum),
                    windowSize = WindowWidthSizeClass.Expanded
                )
            }
        }
    }
}