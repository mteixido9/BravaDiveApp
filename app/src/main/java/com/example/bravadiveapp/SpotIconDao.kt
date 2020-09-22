package com.example.bravadiveapp

import androidx.room.*


@Dao
interface SpotIconDao {
    @Query("SELECT * FROM SpotIcon")
    fun getAll(): List<SpotIcon>

    @Query("SELECT * FROM SpotIcon WHERE fkSpotId IN (:spotId)")
    fun loadSpotIconBySpotId(spotId: Int):List<SpotIcon>

    @Query("SELECT * FROM SpotIcon WHERE fkIconId IN (:iconId)")
    fun loadSpotIconByIconId(iconId: Int):List<SpotIcon>

    @Insert
    fun insert(spotIcon: SpotIcon)
    @Insert
    fun insertAll(spotIcons: List<SpotIcon>)
    @Update
    fun update(spotIcon: SpotIcon)
    @Delete
    fun delete(spotIcon: SpotIcon)
}