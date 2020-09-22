package com.example.bravadiveapp

import androidx.room.*

@Dao
interface SpotDao {

    @Query("SELECT * FROM Spot")
    fun getAll(): List<Spot>
    //TODO get de varios filtros

    @Query("SELECT * FROM Spot WHERE spotId IN (:spotId)")
    fun loadSpotbyId(spotId: Int):Spot

    @Insert
    fun insert(spot: Spot)
    @Insert
    fun insertAll(spots: List<Spot>)
    @Update
    fun update(spot: Spot)
    @Delete
    fun delete(spot: Spot)



}