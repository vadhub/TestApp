package com.abg.testapp.ui.cameras

import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.abg.testapp.model.Camera
import com.abg.testapp.ui.theme.Beige

@Composable
fun CamerasList(cameras: List<Camera>, onClickFavorite: (Int) -> Unit, onClickRenamed: (Int, String) -> Unit) {
    LazyColumn(Modifier.background(color = Beige)) {
        items(cameras.size) {
            CameraItem(cameras[it], onClickFavorite, onClickRenamed)
        }
    }
}