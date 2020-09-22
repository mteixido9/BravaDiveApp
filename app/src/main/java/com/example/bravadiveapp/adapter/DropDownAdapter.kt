package com.example.bravadiveapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.bravadiveapp.Icon
import com.example.bravadiveapp.R


class DropDownAdapter(context: Context, private var iconsList: List<Icon>) : BaseAdapter() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view = inflater.inflate(R.layout.custom_spinner_row, parent, false)
        val viewHolder = ItemHolder(view)



        viewHolder.textView.text = iconsList[position].name
        viewHolder.imageViewIcon.setImageResource(iconsList[position].url)

        return view
    }

    override fun getCount(): Int {
        return iconsList.size
    }

    override fun getItem(position: Int): Any {
        return iconsList[position]
    }

    override fun getItemId(position: Int): Long {
        return iconsList[position].iconId.toLong()
    }


    private class ItemHolder(row: View?) {

        val textView: TextView = row?.findViewById(R.id.spinnerTv) as TextView
        val imageViewIcon: ImageView = row?.findViewById(R.id.iVTechnicalIconSpinner) as ImageView
    }

}