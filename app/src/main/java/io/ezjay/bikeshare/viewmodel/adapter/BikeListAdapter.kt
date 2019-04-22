package io.ezjay.bikeshare.viewmodel.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import io.ezjay.bikeshare.model.Bike

class BikeListAdapter (
    private val bikes : List<Bike>
) : RecyclerView.Adapter<BikeHolder>() {

    override fun onCreateViewHolder(p: ViewGroup, v: Int): BikeHolder {
        val inflater = LayoutInflater.from(p.context)
        return BikeHolder(inflater, p)
    }

    override fun getItemCount(): Int {
        return this.bikes.size
    }

    override fun onBindViewHolder(holder: BikeHolder, i: Int) {
        holder.bind(this.bikes[i])
    }
}