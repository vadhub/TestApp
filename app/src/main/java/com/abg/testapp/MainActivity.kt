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
import com.abg.testapp.model.Camera
import com.abg.testapp.model.Door
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

        val favoriteCamera: (Camera) -> Unit = {
            viewModel.insertFavoriteCamera(it)
        }

        val editCamera: (Camera) -> Unit = {
            viewModel.insertFavoriteCamera(it)
        }

        val favoriteDoor: (Door) -> Unit = {
            viewModel.insertFavoriteDoor(it)
        }

        val editDoor: (Door) -> Unit = {
            viewModel.insertRenamedDoor(it)
        }

        setContent {
            TestAppTheme {
                Column(Modifier.background(Beige)) {
                    TopBar()
                    TabLayout(
                        { DoorsScreen(doors = viewModel.doors.collectAsState(), favoriteDoor, editDoor) },
                        { CamerasScreen(cameras = viewModel.cameras.collectAsState(), favoriteCamera, editCamera)})
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    CenterAlignedTopAppBar(
        title = { Text(text = stringResource(R.string.app_name), fontSize = 18.sp) },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Beige)
    )
}

