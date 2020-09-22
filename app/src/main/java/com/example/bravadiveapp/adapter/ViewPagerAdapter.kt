package com.example.bravadiveapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bravadiveapp.R
import com.example.bravadiveapp.SpotImage
import kotlinx.android.synthetic.main.fragment_image.view.*

class ViewPagerAdapter(val spotImageList: List<SpotImage>) : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {


    class ViewPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_image, parent, false)
        return ViewPagerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return spotImageList.size

    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
    val spotImage = spotImageList[position]
        holder.itemView.iVfragment.setImageResource(spotImage.imageid)
    }




}