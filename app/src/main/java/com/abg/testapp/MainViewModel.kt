package com.abg.testapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.abg.testapp.data.MainRepository
import com.abg.testapp.data.Resource
import com.abg.testapp.model.Camera
import com.abg.testapp.model.Door
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**ViewModel for handle result from repository
 * @param repository is instance repository
 * */
class MainViewModel(private val repository: MainRepository) : ViewModel() {

    private val _doors = MutableStateFlow<Resource<List<Door>?>?>(null) // we can get null from response
    val doors: StateFlow<Resource<List<Door>?>?> = _doors

    private val _cameras = MutableStateFlow<Resource<List<Camera>?>?>(null)
    val cameras: StateFlow<Resource<List<Camera>?>?> = _cameras

    /** if in (DB) database Door is empty then connect to remote server and save to DB
     * else get data from DB
     */
    fun getDoors() = viewModelScope.launch(Dispatchers.IO) {
        if (repository.checkIsEmptyDoorDB()) {
            _doors.value = Resource.Loading
            repository.getDoorsFromRemote() // get data from remote server
            repository.getDoorsFromDB().collectLatest {
                _doors.value = Resource.Success(it)
            }
            Log.d("#connect", "getDoors")
        } else {
            Log.d("#db", "getDoors")
            _doors.value = Resource.Loading
            repository.getDoorsFromDB().collectLatest {
                _doors.value = Resource.Success(it)
            }
        }
    }

    /**
     * forced refresh doors
     * */
    fun refreshDoors() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteDoorsAll()
        _doors.value = Resource.Loading
        repository.getDoorsFromRemote()
        repository.getDoorsFromDB().collectLatest {
            _doors.value = Resource.Success(it)
        }
    }

    /** insert favorite id door in DB
     * @param id is id door*/
    fun insertFavoriteDoor(id: Int) = viewModelScope.launch {
        repository.insertOrUpdateFavoriteDoor(id)
    }

    /**
     * insert renamed door in DB
     * @param id is id door
     * @param newName is new name door
     * */
    fun insertRenamedDoor(id: Int, newName: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertOrUpdateRenamedDoor(id, newName)
    }

    /** if in (DB) database Camera is empty then connect to remote server and save to DB
     * else get data from DB
     */
    fun getCameras() = viewModelScope.launch(Dispatchers.IO) {
        if (repository.checkIsEmptyCameraDB()) {
            _cameras.value = Resource.Loading
            repository.getCamerasFromRemote()
            repository.getCamerasFromDB().collectLatest {
                _cameras.value = Resource.Success(it)
            }
            Log.d("#connect", "getCameras")
        } else {
            Log.d("#db", "getCameras")
            _cameras.value = Resource.Loading
            repository.getCamerasFromDB().collectLatest {
                _cameras.value = Resource.Success(it)
            }
        }
    }

    /**
     * insert favorite camera
     * @param id is id camera
     * */
    fun insertFavoriteCamera(id: Int) = viewModelScope.launch {
        repository.insertOrUpdateFavoriteCamera(id)
    }

    /**
     * forced refresh camera
     * */
    fun refreshCameras() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteCamerasAll()
        _cameras.value = Resource.Loading
        repository.getCamerasFromRemote()

        repository.getCamerasFromDB().collectLatest {
            _cameras.value = Resource.Success(it)
        }
    }

}

class MainViewModelFactory(private val repository: MainRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}