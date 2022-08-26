package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.udacity.asteroidradar.models.Asteroid

@Dao
interface Dao_Database {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg asteroids: Asteroid)

//    @Query("SELECT * from asteroid_table WHERE closeApproachDate = :key")
//    suspend fun getDescribedData(key: Long): Asteroid?

    @Query("SELECT * FROM asteroid_table ORDER BY closeApproachDate DESC")
    fun getAllSavedAsteroids(): LiveData<List<Asteroid>?>

    @Query("SELECT * FROM asteroid_table WHERE closeApproachDate = :day")               // Worked
    fun getTodayAsteroids(day : String): LiveData<List<Asteroid>?>

    @Query("SELECT * FROM asteroid_table WHERE closeApproachDate BETWEEN :day AND :seventhDay ORDER BY closeApproachDate DESC")
    fun getWeekAsteroids(day : String, seventhDay : String): LiveData<List<Asteroid>?>

}
