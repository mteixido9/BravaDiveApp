package com.example.bravadiveapp.activity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.bravadiveapp.Icon
import com.example.bravadiveapp.R
import com.example.bravadiveapp.Spot
import com.example.bravadiveapp.adapter.DropDownAdapter
import com.example.bravadiveapp.viewmodel.MapsActivityViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import kotlinx.android.synthetic.main.activity_detail.iVLogo
import kotlinx.android.synthetic.main.activity_maps.*
import kotlinx.coroutines.*


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var viewModel: MapsActivityViewModel

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, MapsActivity::class.java)

        }
    }

    var iconsList = listOf<Icon>()

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        (supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment)?.getMapAsync(this)

        viewModel =
            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(
                MapsActivityViewModel::class.java
            )
        //Adding logo to the bar.
        iVLogo.setImageResource(R.mipmap.brava_dive_oval)

        // Adding the adatper to the spinner
        createSpinner()


        spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                CoroutineScope(Dispatchers.IO).launch {

                    if (id == -1L) {
                        val spotByIconList = viewModel.getSpotList()
                        updateMap(spotByIconList, false)
                    } else {
                        val spotByIconList = viewModel.getSpotByIcon(id.toInt())

                        updateMap(spotByIconList, false)
                    }

                }
            }
        }
    }


    override fun onResume() {
        super.onResume()

        CoroutineScope(Dispatchers.IO).launch {
            //Updating map

            updateMap(viewModel.getSpotList(), false)
        }
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        CoroutineScope(Dispatchers.IO).launch {
            //Updating map
            updateMap(viewModel.getSpotList(), true)
        }
    }


    fun getMarkerIcon(colorId: Int): BitmapDescriptor? {
        val hsv = FloatArray(3)
        val color = ContextCompat.getColor(this, colorId)

        Color.colorToHSV(color, hsv)
        return BitmapDescriptorFactory.defaultMarker(hsv[0])
    }


    suspend fun updateMap(spotList: List<Spot>, zoom: Boolean) {
        withContext(Dispatchers.Main) {
            val markerList = mutableListOf<Marker>()
            mMap.clear()


            //Leemos todos los spots de db, hacemos un for y creamos un marker para cada spot con su LatLng y titulo. Luego lo añadimos a una lista.
            spotList.forEach {
                val marker = MarkerOptions().position(
                    LatLng(
                        it.latitude.toDouble(),
                        it.longitude.toDouble()
                    )
                ).title(it.name)
                marker.title(it.name).snippet(it.done.toString())


                //Cambio de color el marker
                if (it.done) {
                    marker.icon(getMarkerIcon(R.color.bright_green))
                } else {
                    marker.icon(getMarkerIcon(R.color.darkBlue))
                }

                //Añado markers al map
                markerList.add(mMap.addMarker(marker))
            }

            //Zoom mínimo y typo de mapa.

            if (markerList.isNotEmpty() && zoom) {
                mMap.moveCamera(CameraUpdateFactory.newLatLng(markerList[0].position))
            }
            mMap.setMinZoomPreference(10.5f)
            mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE

            //Seteamos la navegacion mediante el marker al DetailActivity.
            mMap.setOnMarkerClickListener { marker ->
                marker.title?.let { markerTitle ->
                    spotList.forEach {
                        if (markerTitle.contentEquals(it.name))
                            startActivity(DetailActivity.getIntent(this@MapsActivity, it))
                    }
                }
                true
            }
        }
    }
    fun createSpinner(){

        CoroutineScope(Dispatchers.Main).launch {

            iconsList = viewModel.getIconList()
            Log.w("MARC", iconsList.size.toString())

            if (iconsList.size == 1) {
                delay(1000)
                createSpinner()
            }else{

                withContext(Dispatchers.Main) {

                    val dropDownAdapter = DropDownAdapter(this@MapsActivity, iconsList)
                    spinner.adapter = dropDownAdapter
                }


            }

        }


    }

}