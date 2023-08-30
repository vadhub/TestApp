package com.abg.testapp.ui.cameras

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.abg.testapp.data.Resource
import com.abg.testapp.model.Camera

@Composable
fun CamerasScreen(cameras: State<Resource<List<Camera>?>?>, onClickFavorite: (Camera) -> Unit, onClickRenamed: (Camera) -> Unit) {

    cameras.value?.let {
        when (it) {
            is Resource.Failure -> {

            }

            Resource.Loading -> {

            }

            is Resource.Success -> {

                Column {
                    if (it.result != null) {
                        CamerasList(cameras = it.result, onClickFavorite, onClickRenamed)
                    }
                }
            }
        }
    }
}