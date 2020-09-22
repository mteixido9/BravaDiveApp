package com.example.bravadiveapp

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Spot(var name: String,var text: String, var done: Boolean,var latitude: Float, var longitude: Float) {

    @PrimaryKey(autoGenerate = true)

    var spotId : Int = 0
}

