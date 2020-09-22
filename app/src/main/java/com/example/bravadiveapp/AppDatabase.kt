package com.example.bravadiveapp

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities= [Spot::class,SpotIcon::class,SpotImage::class,Icon::class], version= 1)

abstract class AppDatabase: RoomDatabase() {

    abstract fun spotDao() : SpotDao
    abstract fun spotIconDao() : SpotIconDao
    abstract fun spotImageDao() : SpotImageDao
    abstract fun iconDao(): IconDao

}