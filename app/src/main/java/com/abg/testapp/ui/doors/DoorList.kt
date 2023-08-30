package com.abg.testapp.ui.doors

import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.abg.testapp.model.Door
import com.abg.testapp.ui.theme.Beige

@Composable
fun DoorsList(doors: List<Door>, onClickFavorite: (Door) -> Unit, onClickRenamed: (Door) -> Unit) {

        LazyColumn(Modifier.background(color = Beige)) {
            items(doors.size) {
                DoorItem(doors[it], onClickFavorite, onClickRenamed)
            }
        }

}