package io.ezjay.bikeshare.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import io.ezjay.bikeshare.entity.Ride

class RideArrayAdapter(
    private val values : List<Ride>
) : RecyclerView.Adapter<RideHolder>() {

    override fun onCreateViewHolder(p: ViewGroup, v: Int): RideHolder {
        val inflater = LayoutInflater.from(p.context)
        return RideHolder(inflater, p)
    }

    override fun getItemCount(): Int {
        return this.values.size
    }

    override fun onBindViewHolder(holder: RideHolder, i: Int) {
        holder.bind(this.values[i])
    }
}