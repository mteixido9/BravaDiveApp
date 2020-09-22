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
import com.example.bravadiveapp.Icon
import com.example.bravadiveapp.R
import com.example.bravadiveapp.SpotIcon


class TechnicIconAdapter(val iconList: List<Icon>, var context: Context) :
    RecyclerView.Adapter<TechnicIconAdapter.TechnicIconViewHolder>() {

    // Creo la iconsList a partir de la lista del DropDownAdapter que he pasado como parametro. Asi puedo añadir el texto al Toast.


    class TechnicIconViewHolder(root: View) : RecyclerView.ViewHolder(root) {

        val iVTechnicalIcon = root.findViewById<ImageView>(R.id.iVTechnicalIcon)

        fun updateIcon(icon: Icon) {
            iVTechnicalIcon.setImageResource(icon.url)

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TechnicIconViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_technical_aspects, parent, false)

        return TechnicIconViewHolder(view)
    }

    override fun onBindViewHolder(holder: TechnicIconViewHolder, position: Int) {
        holder.updateIcon(iconList[position])
        holder.iVTechnicalIcon.setOnClickListener {
            Toast.makeText(context, iconList[position].name, Toast.LENGTH_SHORT).apply { setGravity(Gravity.CENTER, 0, 180) }.show()


        }
    }

    override fun getItemCount(): Int {
        return iconList.size
    }
}

