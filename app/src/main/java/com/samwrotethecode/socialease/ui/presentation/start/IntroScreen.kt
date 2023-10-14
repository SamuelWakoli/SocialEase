package com.samwrotethecode.socialease.ui.presentation.start

import android.app.Activity
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
import androidx.compose.foundation.layout.widthIn
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.samwrotethecode.socialease.R
import com.samwrotethecode.socialease.ui.presentation.navigation.Screens
import com.samwrotethecode.socialease.ui.presentation.start.models.IntroScreenData
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IntroScreen(
    windowSize: WindowWidthSizeClass,
    navHostController: NavHostController
) {
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

            when (windowSize) {
                WindowWidthSizeClass.Compact -> {
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
                                    text = stringResource(id = IntroScreenData[currentPage].title),
                                    style = TextStyle(
                                        color = Color.White,
                                        fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                                        fontWeight = MaterialTheme.typography.headlineLarge.fontWeight,
                                        shadow = Shadow(color = Color.Black, blurRadius = 32f)
                                    ),
                                )
                                Spacer(modifier = Modifier.height(20.dp))
                                Text(
                                    text = stringResource(id = IntroScreenData[currentPage].subTitle),
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
                                        text = stringResource(id = IntroScreenData[currentPage].content),
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

                else -> {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Image(
                            painter = painterResource(id = IntroScreenData[currentPage].backgroundImage),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop,
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp)
                                .verticalScroll(rememberScrollState()),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Spacer(modifier = Modifier.height(20.dp))
                                Text(
                                    text = stringResource(id = IntroScreenData[currentPage].title),
                                    style = TextStyle(
                                        color = Color.White,
                                        fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                                        fontWeight = MaterialTheme.typography.headlineLarge.fontWeight,
                                        shadow = Shadow(color = Color.Black, blurRadius = 32f)
                                    ),
                                )
                                Spacer(modifier = Modifier.height(20.dp))
                                Text(
                                    text = stringResource(id = IntroScreenData[currentPage].subTitle),
                                    style = TextStyle(
                                        color = Color.White,
                                        fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                                        fontWeight = MaterialTheme.typography.headlineSmall.fontWeight,
                                        shadow = Shadow(color = Color.Black, blurRadius = 32f)
                                    ),
                                )
                                Spacer(modifier = Modifier.height(20.dp))
                            }
                            Spacer(modifier = Modifier.width(20.dp))
                            Column(
                                modifier = Modifier.padding(32.dp)
                            ) {
                                Card (
                                    modifier = Modifier.widthIn(max = 400.dp)
                                ) {
                                    Text(
                                        text = stringResource(id = IntroScreenData[currentPage].content),
                                        modifier = Modifier.padding(8.dp),
                                        style = TextStyle(
                                            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                                            fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
                                        ),
                                    )
                                }
                            }
                        }
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
                    TextButton(onClick = {
                        navHostController.navigate(Screens.SignInScreen.route) {
                            launchSingleTop = true
                        }
                    }) {
                        Text(text = stringResource(R.string.skip))
                    }

                    if (pagerState.currentPage != pagerState.initialPage) {
                        Spacer(modifier = Modifier.width(24.dp))
                        IconButton(onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(
                                    page = pagerState.currentPage - 1
                                )
                            }
                        }) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                        }
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
                        Button(onClick = {
                            navHostController.navigate(Screens.SignInScreen.route) {
                                launchSingleTop = true
                            }
                        }) {
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

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun IntroScreenPreview() {
    IntroScreen(
        windowSize = calculateWindowSizeClass(
            LocalContext.current as Activity
        ).widthSizeClass,
        navHostController = rememberNavController(),
    )
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(
    showBackground = true, showSystemUi = true, device = "spec:parent=pixel_5,orientation=landscape"
)
@Composable
fun IntroScreenPreviewRotated() {
    IntroScreen(
        windowSize = calculateWindowSizeClass(
            LocalContext.current as Activity
        ).widthSizeClass,
        navHostController = rememberNavController()
    )
}