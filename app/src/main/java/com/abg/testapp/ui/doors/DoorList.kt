package com.abg.testapp.ui.doors

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.abg.testapp.model.Door

@Composable
fun DoorsList(doors: List<Door>) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn {
            items(doors.size) {
                DoorItem(doors[it].name, doors[it].snapshot, doors[it].favorites)
            }
        }
    }
}