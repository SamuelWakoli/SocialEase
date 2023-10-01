package com.samwrotethecode.socialease.ui.presentation.composables

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BrokenImage
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation

@Composable
fun CoilImage(
    photoUrl: Uri? = null,
    size: Dp = 46.dp
) {
    Box(
        modifier = Modifier.size(size),
        contentAlignment = Alignment.Center,
    ) {

        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(photoUrl ?: "https://picsum.photos/200").crossfade(true)
                .transformations(CircleCropTransformation()).build()
        )

        if (painter.state is AsyncImagePainter.State.Loading) {
            CircularProgressIndicator(
                strokeWidth = 2.dp,
                color = MaterialTheme.colorScheme.primary,
            )
        }


        if (painter.state is AsyncImagePainter.State.Empty || painter.state is AsyncImagePainter.State.Error) {
            // Do something if image url is empty
            Icon(
                imageVector = Icons.Outlined.BrokenImage, contentDescription = null
            )
        }

//        if (painter.state is AsyncImagePainter.State.Success) {
//            // This will be executed during the first composition if the image is in the memory cache.
//        }
        Image(
            painter = painter,
            contentDescription = "Avatar image",
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CoilImagePreview() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        CoilImage()
    }
}