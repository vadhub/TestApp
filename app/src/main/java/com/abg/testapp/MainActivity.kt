package com.abg.testapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.abg.testapp.data.DatabaseInstance
import com.abg.testapp.data.MainRepository
import com.abg.testapp.data.RemoteInstance
import com.abg.testapp.ui.TabLayout
import com.abg.testapp.ui.cameras.CamerasScreen
import com.abg.testapp.ui.doors.DoorsScreen
import com.abg.testapp.ui.theme.Beige
import com.abg.testapp.ui.theme.TestAppTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel> {
        MainViewModelFactory(
            MainRepository(
                RemoteInstance.client(),
                DatabaseInstance.provideRealm()
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getDoors()
        viewModel.getCameras()

        //insert to database favorite camera object
        val favoriteCamera: (Int) -> Unit = {
            viewModel.insertFavoriteCamera(it)
        }
        //insert to database renamed camera object
        val editCamera: (Int, String) -> Unit = { id, newName ->
            viewModel.insertRenamedCamera(id, newName)
        }

        //insert to database favorite door object
        val favoriteDoor: (Int) -> Unit = {
            viewModel.insertFavoriteDoor(it)
        }

        //insert to database renamed door object
        val editDoor: (Int, String) -> Unit = { id, newName ->
            viewModel.insertRenamedDoor(id, newName)
        }

        //pull refresh
        val onRefreshDoor: () -> Unit = {
            viewModel.refreshDoors()
        }

        val onRefreshCamera: () -> Unit = {
            viewModel.refreshCameras()
        }

        setContent {
            TestAppTheme {

                Column(Modifier.background(Beige)) {
                    TopBar()
                    TabLayout(
                        { DoorsScreen(doors = viewModel.doors.collectAsState(), favoriteDoor, editDoor, onRefreshDoor) },
                        { CamerasScreen(cameras = viewModel.cameras.collectAsState(), favoriteCamera, editCamera, onRefreshCamera)})
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    CenterAlignedTopAppBar(
        title = { Text(text = stringResource(R.string.my_home), fontSize = 18.sp) },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Beige)
    )
}

