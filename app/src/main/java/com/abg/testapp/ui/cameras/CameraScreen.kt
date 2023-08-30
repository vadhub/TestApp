package com.abg.testapp.ui.cameras

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.abg.testapp.data.Resource
import com.abg.testapp.model.Camera
import com.abg.testapp.ui.theme.Beige

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CamerasScreen(
    cameras: State<Resource<List<Camera>?>?>,
    onClickFavorite: (Int) -> Unit,
    onClickRenamed: (Int, String) -> Unit,
    onRefresh: () -> Unit
) {

    val pullRefreshState = rememberPullRefreshState(false, { onRefresh.invoke() })

    cameras.value?.let {
        when (it) {
            is Resource.Failure -> {
                // in case fail show toast
                Toast.makeText(LocalContext.current, "Fail", Toast.LENGTH_SHORT).show()
            }

            Resource.Loading -> {
                // can use progress bar or shimmer
                Toast.makeText(LocalContext.current, "Loading", Toast.LENGTH_SHORT).show()
            }

            is Resource.Success -> {
                Box(Modifier.pullRefresh(pullRefreshState)) {
                    Column {
                        if (it.result != null) {
                            CamerasList(cameras = it.result, onClickFavorite, onClickRenamed)
                        }
                    }

                    PullRefreshIndicator(
                        modifier = Modifier.align(Alignment.TopCenter),
                        refreshing = false,
                        state = pullRefreshState,
                        backgroundColor = Beige,
                    )
                }

            }
        }
    }
}