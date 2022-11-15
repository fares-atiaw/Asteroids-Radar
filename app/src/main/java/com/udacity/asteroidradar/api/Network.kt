package com.udacity.asteroidradar.api

import com.udacity.asteroidradar.models.PictureOfDay
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://api.nasa.gov/planetary/apod?api_key=YOUR_API_KEY
//https://api.nasa.gov/neo/rest/v1/feed?start_date=START_DATE&end_date=END_DATE&api_key=YOUR_API_KEY
const val base_url = "https://api.nasa.gov/"
const val api_key = "DEMO_KEY"

    object Network {
        private val retrofit1 = Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(base_url)
                .build()

        val service: EndPoints by lazy { retrofit1.create(EndPoints::class.java) }
    }

    interface EndPoints {
        @GET("neo/rest/v1/feed?api_key=${api_key}")
        suspend fun getAsteroids(): String

        @GET("neo/rest/v1/feed?api_key=${api_key}")
        suspend fun getNextAsteroids(
            @Query("start_date") start_date: String,
            @Query("end_date") end_date: String
        ): String

        @GET("planetary/apod?api_key=${api_key}")
        suspend fun getDayPicture(): PictureOfDay?
    }

/*
@GET("neo/rest/v1/feed?api_key=${api_key}")
        suspend fun getAsteroids(
                    @Query("start_date") start_date: String,
                    @Query("end_date") end_date: String
             ): List<Asteroid>?
 */