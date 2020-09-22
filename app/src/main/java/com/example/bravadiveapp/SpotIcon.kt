package com.example.bravadiveapp


import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Spot::class,
            parentColumns = arrayOf("spotId"),
            childColumns = arrayOf("fkSpotId"),
            onDelete = ForeignKey.SET_NULL
        ),
        ForeignKey(
            entity = Icon::class,
            parentColumns = arrayOf("iconId"),
            childColumns = arrayOf("fkIconId"),
            onDelete = ForeignKey.SET_NULL
        ),
    )
)

data class SpotIcon(var fkIconId: Int, var fkSpotId: Int) {

    @PrimaryKey(autoGenerate = true)

    var spotIconId: Int = 0
}