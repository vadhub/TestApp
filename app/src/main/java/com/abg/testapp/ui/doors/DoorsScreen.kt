package com.abg.testapp.ui.doors

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.abg.testapp.MainViewModel
import com.abg.testapp.data.Resource

@Composable
fun DoorsScreen(viewModel: MainViewModel) {

    val doors = viewModel.doors.collectAsState()

    doors.value?.let {
        when (it) {
            is Resource.Failure -> {

            }

            Resource.Loading -> {

            }

            is Resource.Success -> {
                Column {
                    if (it.result != null) {
                        DoorsList(doors = it.result)
                    }
                }
            }
        }
    }

}