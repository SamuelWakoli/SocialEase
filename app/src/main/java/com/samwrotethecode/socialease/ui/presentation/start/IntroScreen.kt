package com.samwrotethecode.socialease.ui.presentation.start

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.FiberManualRecord
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samwrotethecode.socialease.ui.presentation.start.models.IntroScreenData
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IntroScreen() {
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
        pageCount = { IntroScreenData.size })

    val scope = rememberCoroutineScope()

    Box(Modifier.fillMaxSize()) {
        HorizontalPager(
            state = pagerState,
            pageSize = PageSize.Fill,
        ) { currentPage ->
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = IntroScreenData[currentPage].backgroundImage),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = IntroScreenData[currentPage].title,
                            style = TextStyle(
                                color = Color.White,
                                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                                fontWeight = MaterialTheme.typography.headlineLarge.fontWeight,
                                shadow = Shadow(color = Color.Black, blurRadius = 32f)
                            ),
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = IntroScreenData[currentPage].subTitle,
                            style = TextStyle(
                                color = Color.White,
                                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                                fontWeight = MaterialTheme.typography.headlineSmall.fontWeight,
                                shadow = Shadow(color = Color.Black, blurRadius = 32f)
                            ),
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                    Column {
                        Card {
                            Text(
                                text = IntroScreenData[currentPage].content,
                                modifier = Modifier.padding(8.dp),
                                style = TextStyle(
                                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                                    fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
                                ),
                            )
                        }
                        Spacer(modifier = Modifier.height(100.dp))
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            Card(
                modifier = Modifier.clip(shape = MaterialTheme.shapes.large)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(8.dp),
                ) {
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = "Skip")
                    }
                    Spacer(modifier = Modifier.width(24.dp))
                    if (pagerState.currentPage != pagerState.initialPage)
                        IconButton(onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(
                                    page = pagerState.currentPage - 1
                                )
                            }
                        }) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    Spacer(modifier = Modifier.width(24.dp))
                    IntroScreenData.forEach {
                        Icon(
                            imageVector = Icons.Default.FiberManualRecord,
                            contentDescription = null,
                            tint = if (pagerState.currentPage == IntroScreenData.indexOf(it)) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                Color.Unspecified
                            },
                            modifier = Modifier
                                .size(
                                    if (pagerState.currentPage == IntroScreenData.indexOf(it)) 16.dp
                                    else 10.dp
                                )
                                .animateContentSize(),
                        )
                    }
                    Spacer(modifier = Modifier.width(24.dp))
                    if (pagerState.currentPage == IntroScreenData.lastIndex)
                        Button(onClick = { /*TODO*/ }) {
                            Text(text = "Sign in")
                        }
                    else IconButton(onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(
                                page = pagerState.currentPage + 1
                            )
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowForward, contentDescription = "Forward"
                        )
                    }
                }
            }
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun IntroScreenPreview() {
    IntroScreen()
}

@Preview(
    showBackground = true, showSystemUi = true, device = "spec:parent=pixel_5,orientation=landscape"
)
@Composable
fun IntroScreenPreviewRotated() {
    IntroScreen()
}