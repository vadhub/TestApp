package com.abg.testapp.ui.doors

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.platform.LocalContext
import com.abg.testapp.data.Resource
import com.abg.testapp.model.Door

@Composable
fun DoorsScreen(
    doors: State<Resource<List<Door>?>?>,
    onClickFavorite: (Int) -> Unit,
    onClickRenamed: (Int, String) -> Unit
) {

    doors.value?.let {
        when (it) {
            is Resource.Failure -> {
                Toast.makeText(LocalContext.current, "Fail", Toast.LENGTH_SHORT).show()
            }

            Resource.Loading -> {
                Toast.makeText(LocalContext.current, "Loading", Toast.LENGTH_SHORT).show()
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