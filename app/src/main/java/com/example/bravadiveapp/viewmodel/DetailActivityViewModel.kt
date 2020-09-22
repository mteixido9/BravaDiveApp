package com.example.bravadiveapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.bravadiveapp.App
import com.example.bravadiveapp.Spot
import com.example.bravadiveapp.SpotIcon
import com.example.bravadiveapp.SpotImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DetailActivityViewModel(application: Application) : AndroidViewModel(application) {


    suspend fun getSpot(spotId: Int): Spot {
        return withContext(Dispatchers.IO) {
            return@withContext App.getDatabase(getApplication()).spotDao().loadSpotbyId(spotId)
        }
    }

    suspend fun getIcons(spotId: Int): List<SpotIcon> {
        return withContext(Dispatchers.IO) {
            return@withContext App.getDatabase(getApplication()).spotIconDao().loadSpotIconById(spotId)
        }
    }

    suspend fun getImages(spotId: Int): List<SpotImage> {
        return withContext(Dispatchers.IO) {
            return@withContext App.getDatabase(getApplication()).spotImageDao().loadSpotImagebyId(spotId)
        }
    }

    suspend fun updateSpot(spot: Spot){
        withContext(Dispatchers.IO){
            App.getDatabase(getApplication()).spotDao().update(spot)

        }

    }


}