package com.example.bravadiveapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Icon(var name: String,var url: Int) {

    @PrimaryKey(autoGenerate = true)

    var iconId : Int = 0

}