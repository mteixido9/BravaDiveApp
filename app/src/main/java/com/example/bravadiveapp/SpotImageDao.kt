package com.example.bravadiveapp

import androidx.room.*


@Dao
interface SpotImageDao {

    @Query("SELECT * FROM SpotImage")
    fun getAll(): List<SpotImage>

    @Query("SELECT * FROM SpotImage WHERE fkSpotId IN (:spotId)")
    fun loadSpotImagebyId(spotId: Int):List<SpotImage>

    @Insert
    fun insert(spotImage: SpotImage)

    @Insert
    fun insertAll(spotImage: List<SpotImage>)

    @Update
    fun update(spotImage: SpotImage)

    @Delete
    fun delete(spotImage: SpotImage)
}