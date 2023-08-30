package com.abg.testapp.ui.doors

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.abg.testapp.data.Resource
import com.abg.testapp.model.Door

@Composable
fun DoorsScreen(doors: State<Resource<List<Door>?>?>, onClickFavorite: (Door) -> Unit, onClickRenamed: (Door) -> Unit) {

    doors.value?.let {
        when (it) {
            is Resource.Failure -> {

            }

            Resource.Loading -> {

            }

            is Resource.Success -> {

                Column {
                    if (it.result != null) {
                        DoorsList(doors = it.result, onClickFavorite, onClickRenamed)
                    }
                }
            }
        }
    }

}