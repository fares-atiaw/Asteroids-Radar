package com.udacity.asteroidradar.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "picture_of_day_table")
data class PictureOfDay(
    @SerializedName("media_type") val mediaType: String,
    val title: String,
    @PrimaryKey
    val url: String
    )