package com.abg.testapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.abg.testapp.R
import com.abg.testapp.ui.theme.Beige
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabLayout(cameraScreen: @Composable () -> Unit, doorScreen: @Composable () -> Unit) {
    val context = LocalContext.current
    val tabsList = context.resources.getStringArray(R.array.tabs)
    val pagerState = rememberPagerState(tabsList.size)
    val tabIndex = pagerState.currentPage
    val coroutineScope = rememberCoroutineScope()

    Column {
        TabRow(
            selectedTabIndex = tabIndex,
            indicator = { pos ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, pos)
                )
            },
            backgroundColor = Beige,
            contentColor = Color.Blue
        ) {

            tabsList.forEachIndexed { index, text ->
                Tab(
                    selected = false,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = { Text(text = text) }
                )
            }
        }

        HorizontalPager(state = pagerState, modifier = Modifier.weight(1f)) { index ->
            when (index) {
                0 -> doorScreen.invoke()
                1 -> cameraScreen.invoke()
            }
        }
    }
}