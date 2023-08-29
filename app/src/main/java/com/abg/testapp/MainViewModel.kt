package com.abg.testapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.abg.testapp.data.MainRepository
import com.abg.testapp.data.Resource
import com.abg.testapp.model.Camera
import com.abg.testapp.model.Door
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

//ViewModel for handle result from repository
class MainViewModel(repository: MainRepository) : ViewModel() {

    private val _doors = MutableStateFlow<Resource<List<Door>?>?>(null) // we can get null from response
    val doors: StateFlow<Resource<List<Door>?>?> = _doors

    private val _cameras = MutableStateFlow<Resource<List<Camera>?>?>(null)
    val cameras: StateFlow<Resource<List<Camera>?>?> = _cameras

    init {
        viewModelScope.launch {
            _doors.value = Resource.Loading
            _doors.value = repository.getDoorsFromRemote()

            _cameras.value = Resource.Loading
            _cameras.value = repository.getCamerasFromRemote()
        }
    }
}

class MainViewModelFactory(private val repository: MainRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}