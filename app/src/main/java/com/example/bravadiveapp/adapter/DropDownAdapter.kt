package com.example.bravadiveapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.bravadiveapp.R

class DropDownAdapter(context: Context) : BaseAdapter() {

//TODO llamar a la base de datos y llamar la lista de SPOT ICon.
    val iconsList = mutableListOf(
        PhotoText(R.drawable.ic_filtrar,"Todos"),
        PhotoText(R.drawable.ic_beach, "Entrada por playa"),
        PhotoText(R.drawable.ic_parada_descompresio, "Parada de descompresión"),
        PhotoText(R.drawable.ic_nocturna_clean, "Nocturna"),
        PhotoText(R.drawable.ic_luz_de_buceo, "Linterna recomendada"),
        PhotoText(R.drawable.ic_embarcaciones_frecuentes, "Paso frecuente de embarcaciones"),
        PhotoText(R.drawable.ic_corriente, "Corrientes Frecuentes"),
        PhotoText(R.drawable.ic_coral, "Coralígeno"),
        PhotoText(R.drawable.ic_consumo_oxigeno, "Gran consumo de oxigeno"),
        PhotoText(R.drawable.ic_boya, "Fondeo fijo"),
        PhotoText(R.drawable.ic_anchor, "Fondeo"),
        PhotoText(R.drawable.ic_posidonia, "Pradera de Posidonia"),
        PhotoText(R.drawable.ic_prof_max, "Profundidad Maxima"),
        PhotoText(R.drawable.ic_rocas, "Fondo de rocas"),
        PhotoText(R.drawable.ic_sand, "Fondo de arena"),
        PhotoText(R.drawable.ic_vientos_desfavorables, "Vientos desfavorables"),
        PhotoText(R.drawable.ic_starfish, "Precoralígeno")
    )


    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view = inflater.inflate(R.layout.custom_spinner_row, parent, false)
        val viewHolder = ItemHolder(view)


        viewHolder.textView.text = iconsList[position].text
        viewHolder.imageViewIcon.setImageResource(iconsList[position].icon)

        return view

    }

    override fun getCount(): Int {
        return iconsList.size
    }

    override fun getItem(position: Int): Any {
        return iconsList[position]
    }

    override fun getItemId(position: Int): Long {
        return iconsList[position].icon.toLong()
    }


    private class ItemHolder(row: View?) {

        val textView: TextView = row?.findViewById(R.id.spinnerTv) as TextView
        val imageViewIcon: ImageView = row?.findViewById(R.id.iVTechnicalIconSpinner) as ImageView

    }

    class PhotoText(var icon: Int, var text: String) {

    }


}