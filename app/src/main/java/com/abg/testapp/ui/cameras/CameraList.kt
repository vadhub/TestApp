package com.abg.testapp.ui.cameras

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.abg.testapp.model.Camera
import com.abg.testapp.ui.theme.Beige

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CamerasList(cameras: List<Camera>, onClickFavorite: (Int) -> Unit) {

    val grouped = cameras.groupBy { it.room ?: "" }

    LazyColumn(Modifier.background(color = Beige)) {
        grouped.forEach { (room, cameras) ->

            stickyHeader {
                Log.d("sticky", room)
                Text(text = room, Modifier.background(Beige).fillMaxWidth().padding(14.dp))

            }
            items(cameras.size) {
                CameraItem(cameras[it], onClickFavorite)
            }
        }
    }
}