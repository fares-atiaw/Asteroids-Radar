package com.udacity.asteroidradar.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import com.udacity.asteroidradar.CustomCalender
import com.udacity.asteroidradar.api.Network
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.models.Asteroid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class Repository(private val database: AsteroidDatabase) {

    val asteroid_allList: LiveData<List<Asteroid>?> = database.AsteroidDatabaseDao.getAllSavedAsteroids()
    val asteroid_todayList: LiveData<List<Asteroid>?> = database.AsteroidDatabaseDao.getTodayAsteroids(CustomCalender().getDay())
    val asteroid_weekList: LiveData<List<Asteroid>?> = database.AsteroidDatabaseDao.getWeekAsteroids(CustomCalender().getDay(), CustomCalender().getSeventhDay())

    /** A playlist that can be shown on the screen. */
    val mediatorList = MediatorLiveData<List<Asteroid>?>()

    init {
        mediatorList.value = asteroid_allList.value

        mediatorList.addSource(asteroid_allList, Observer {
            mediatorList.value = it
        })
       mediatorList.addSource(asteroid_todayList, Observer {
            mediatorList.value = it
        })
       mediatorList.addSource(asteroid_weekList, Observer {
            mediatorList.value = it
        })

    }

    suspend fun refreshAsteroids() {
        withContext(Dispatchers.IO) {
            val playlist = Network.service.getAsteroids()
            val x = JSONObject(playlist)
            val lastData = parseAsteroidsJsonResult(x).toTypedArray()
            database.AsteroidDatabaseDao.insertAll(*lastData)
        }
    }

    fun setAsteroidsAll() {
        mediatorList.value = asteroid_allList.value
    }

    fun setAsteroidsToday() {
        mediatorList.value = asteroid_todayList.value
    }

    fun setAsteroidsWeek() {
        mediatorList.value = asteroid_weekList.value
    }
}





/*
//    mediatorLiveData.addSource(asteroidRepo.getAsteroids()) { t -> mediatorLiveData.value = t }
//
//    fun getAsteroidToday(){
//        mediatorLiveData.addSource(asteroidRepo.getAsteroidToday(dateToday!!)) {
//            t -> mediatorLiveData.value = t
//        }
//
//        asteroids =  asteroidRepo.getAsteroidsWeek(dates[TODAY]!!,dates[LAST_DAY]!!)
*/