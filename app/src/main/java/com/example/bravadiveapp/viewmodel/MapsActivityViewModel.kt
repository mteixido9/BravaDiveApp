package com.example.bravadiveapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.bravadiveapp.App
import com.example.bravadiveapp.Icon
import com.example.bravadiveapp.R
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

    suspend fun getIconList(): List<Icon> {
        return withContext(Dispatchers.IO) {
            val iconsList = App.getDatabase(getApplication()).iconDao().getAll()
            val iconsListMutable = iconsList.toMutableList()
            val iconToAdd = Icon("Todos", R.drawable.ic_filtrar)
            iconToAdd.iconId =-1
            iconsListMutable.add(0,iconToAdd)



            return@withContext iconsListMutable
        }

    }

    suspend fun getSpotByIcon(iconId: Int): List<Spot> {
        return withContext(Dispatchers.IO) {
            //Aqui filtramos por IconId los SpotIcons.
            val listSpotIcon =
                App.getDatabase(getApplication()).spotIconDao().loadSpotIconByIconId(iconId)
            val spotList = mutableListOf<Spot>()
            listSpotIcon.forEach {
                spotList.add(App.getDatabase(getApplication()).spotDao().loadSpotbyId(it.fkSpotId))
            }
            spotList
        }

    }


}
