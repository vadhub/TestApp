package com.abg.testapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.abg.testapp.data.MainRepository
import com.abg.testapp.data.Resource
import com.abg.testapp.model.Camera
import com.abg.testapp.model.Door
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

//ViewModel for handle result from repository
class MainViewModel(private val repository: MainRepository) : ViewModel() {

    private val _doors = MutableStateFlow<Resource<List<Door>?>?>(null) // we can get null from response
    val doors: StateFlow<Resource<List<Door>?>?> = _doors

    private val _cameras = MutableStateFlow<Resource<List<Camera>?>?>(null)
    val cameras: StateFlow<Resource<List<Camera>?>?> = _cameras

    /* if in (DB) database Door is empty then connect to remote server and save to DB
     * else get data from DB
     */
    fun getDoors() = viewModelScope.launch {
        if (repository.checkIsEmptyDoorDB()) {
            _doors.value = Resource.Loading
            _doors.value = repository.getDoorsFromRemote() // get data from remote server
            Log.d("#connect", "getDoors")
            _doors.collectLatest { list ->
                if (list is Resource.Success) {
                    list.result?.let { repository.insertAllDoors(it) } // insert to DB all list of Doors
                }
            }
        } else {
            Log.d("#db", "getDoors")
            _doors.value = Resource.Loading
            repository.getDoorsFromDB().collectLatest {
                _doors.value = Resource.Success(it)
            }
        }
    }

    // insert by id door
    fun insertFavoriteDoor(id: Int) = viewModelScope.launch {
        repository.insertOrUpdateFavoriteDoor(id)
    }

    fun insertRenamedDoor(id: Int, newName: String) = viewModelScope.launch {
        repository.insertOrUpdateRenamedDoor(id, newName)
    }

    fun getCameras() = viewModelScope.launch {
        if (repository.checkIsEmptyCameraDB()) {
            _cameras.value = Resource.Loading
            _cameras.value = repository.getCamerasFromRemote()
            Log.d("#connect", "getCameras")
            _cameras.collectLatest { list ->
                if (list is Resource.Success) {
                    list.result?.let { repository.insertAllCameras(it) }
                }
            }
        } else {
            Log.d("#db", "getCameras")
            _cameras.value = Resource.Loading
            repository.getCamerasFromDB().collectLatest {
                _cameras.value = Resource.Success(it)
            }
        }
    }

    fun insertFavoriteCamera(id: Int) = viewModelScope.launch {
        repository.insertOrUpdateFavoriteCamera(id)
    }

    fun insertRenamedCamera(id: Int, newName: String) = viewModelScope.launch {
        repository.insertOrUpdateRenamedCamera(id, newName)
    }

}

class MainViewModelFactory(private val repository: MainRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}