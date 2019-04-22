package io.ezjay.bikeshare.viewmodel.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import io.ezjay.bikeshare.model.Bike

class BikeDropdownAdapter (
    private val bikes : List<Bike>
) : RecyclerView.Adapter<BikeDropdownHolder>() {

    override fun onCreateViewHolder(p: ViewGroup, v: Int): BikeDropdownHolder {
        val inflater = LayoutInflater.from(p.context)
        return BikeDropdownHolder(inflater, p)
    }

    override fun getItemCount(): Int {
        return this.bikes.size
    }

    override fun onBindViewHolder(holder: BikeDropdownHolder, i: Int) {
        holder.bind(this.bikes[i])
    }
}