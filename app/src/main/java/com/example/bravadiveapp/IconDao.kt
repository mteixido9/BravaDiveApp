package com.example.bravadiveapp

import androidx.room.*

@Dao
interface IconDao {

    @Query("SELECT * FROM Icon")
    fun getAll(): List<Icon>


    @Query("SELECT * FROM Icon WHERE iconId IN (:iconId)")
    fun loadIconById(iconId: Int):Icon

    @Insert
    fun insert(icon: Icon)
    @Insert
    fun insertAll(icon: List<Icon>)
    @Update
    fun update(icon: Icon)
    @Delete
    fun delete(icon: Icon)

}