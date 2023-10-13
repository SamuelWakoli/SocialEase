package com.samwrotethecode.socialease.ui.presentation.home.content

import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.samwrotethecode.socialease.R

@Composable
fun ReadingScreenListItem(modifier: Modifier, title: String?, description: String?) {
    ListItem(
        modifier = modifier,
        headlineContent = {
            Text(text = title.toString())
        },
        supportingContent = {
            Text(text = description.toString())
        },
    )
}

@Preview(showBackground = true)
@Composable
fun ReadingScreenListItemPreview() {

    MaterialTheme {
        ReadingScreenListItem(
            modifier = Modifier,
            title = "Hello World",
            description = stringResource(id = R.string.lorem_ipsum)
        )
    }
}