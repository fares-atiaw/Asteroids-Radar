package com.udacity.asteroidradar.screens.main

import android.app.Application
import androidx.lifecycle.*
import com.udacity.asteroidradar.api.Network
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.models.Asteroid
import com.udacity.asteroidradar.models.PictureOfDay
import com.udacity.asteroidradar.repository.Repository
import kotlinx.coroutines.launch

class VM_Main(application: Application) : AndroidViewModel(application) {

    var repository = Repository(AsteroidDatabase.getInstance(application.baseContext))

    val asteroid_data : LiveData<List<Asteroid>?> = repository.mediatorList

    private var _asteroid_picture = MutableLiveData<PictureOfDay?>()
    val asteroid_picture : LiveData<PictureOfDay?>
        get() = _asteroid_picture

    private var _go_with_details = MutableLiveData<Asteroid?>()
    val go_with_details : LiveData<Asteroid?>
    get() = _go_with_details

    init {
        viewModelScope.launch {
            try {
                launch { repository.refreshAsteroids() }
                launch { _asteroid_picture.value = Network.service.getDayPicture() }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }


    fun goDetails(asteroid : Asteroid)
    {
        _go_with_details.value = asteroid
    }

    fun went() {
        _go_with_details.value = null
    }

    fun allSelected(){
        repository.setAsteroidsAll()
    }
    fun todaySelected(){
        repository.setAsteroidsToday()
    }
    fun weekSelected(){
        repository.setAsteroidsWeek()
    }





    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(VM_Main::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return VM_Main(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}