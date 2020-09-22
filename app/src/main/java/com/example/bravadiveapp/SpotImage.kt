package com.example.bravadiveapp

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(foreignKeys = arrayOf(
    ForeignKey(entity = Spot::class,
        parentColumns = arrayOf("spotId"),
        childColumns = arrayOf("fkSpotId"),
        onDelete = ForeignKey.SET_NULL),
))
data class SpotImage (var imageid: Int, var fkSpotId: Int){

    @PrimaryKey(autoGenerate = true)

    var spotImageId : Int = 0
}