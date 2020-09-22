package com.example.bravadiveapp.adapter


import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.bravadiveapp.App
import com.example.bravadiveapp.R
import com.example.bravadiveapp.SpotIcon


class TechnicIconAdapter(val spotIconList: List<SpotIcon>, var context: Context) :
    RecyclerView.Adapter<TechnicIconAdapter.TechnicIconViewHolder>() {

    // Creo la iconsList a partir de la lista del DropDownAdapter que he pasado como parametro. Asi puedo añadir el texto al Toast.



    class TechnicIconViewHolder(root: View) : RecyclerView.ViewHolder(root){

        val iVTechnicalIcon = root.findViewById<ImageView>(R.id.iVTechnicalIcon)

     fun updateIcon(icon: Int){
         iVTechnicalIcon.setImageResource(icon)

     }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TechnicIconViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_technical_aspects, parent, false)

        return TechnicIconViewHolder(view)
    }

    override fun onBindViewHolder(holder: TechnicIconViewHolder, position: Int) {
        holder.updateIcon(spotIconList[position].iconId)
        holder.iVTechnicalIcon.setOnClickListener {

            //TODO buscar por id no por position. var iconId y filtrar por esto.

                Toast.makeText(context,spotIconList[position].name, Toast.LENGTH_SHORT).apply { setGravity(Gravity.CENTER,0,180)}.show()



        }

    }

    override fun getItemCount(): Int {
        return spotIconList.size
    }


}

