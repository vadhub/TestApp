package com.abg.testapp.ui.cameras

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.platform.LocalContext
import com.abg.testapp.data.Resource
import com.abg.testapp.model.Camera

@Composable
fun CamerasScreen(
    cameras: State<Resource<List<Camera>?>?>,
    onClickFavorite: (Int) -> Unit,
    onClickRenamed: (Int, String) -> Unit
) {

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
                Column {
                    if (it.result != null) {
                        CamerasList(cameras = it.result, onClickFavorite, onClickRenamed)
                    }
                }
            }
        }
    }
}