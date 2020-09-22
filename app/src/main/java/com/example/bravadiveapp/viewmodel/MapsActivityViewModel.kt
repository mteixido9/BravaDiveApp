package com.example.bravadiveapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.bravadiveapp.App
import com.example.bravadiveapp.Spot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MapsActivityViewModel(application: Application) : AndroidViewModel(application) {


    suspend fun getSpotList(): List<Spot> {
        return withContext(Dispatchers.IO) {
            return@withContext App.getDatabase(getApplication()).spotDao()
                .getAll()
        }

    }

    suspend fun getSpotByIcon(iconId: Int): List<Spot> {
        return withContext(Dispatchers.IO) {
            //Aqui filtramos por IconId los SpotIcons.
            var listSpotIcon =
                App.getDatabase(getApplication()).spotIconDao().loadSpotIconByIconId(iconId)
            var spotList = mutableListOf<Spot>()
            listSpotIcon.forEach {
                spotList.add(App.getDatabase(getApplication()).spotDao().loadSpotbyId(it.fkSpotId))
            }
            spotList
        }

    }
}
