package io.ezjay.bikeshare.viewmodel.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import io.ezjay.bikeshare.model.Ride

class RideListAdapter (
    private val rides : List<Ride>
) : RecyclerView.Adapter<RideHolder>() {

    override fun onCreateViewHolder(p: ViewGroup, v: Int): RideHolder {
        val inflater = LayoutInflater.from(p.context)
        return RideHolder(inflater, p)
    }

    override fun getItemCount(): Int {
        return this.rides.size
    }

    override fun onBindViewHolder(holder: RideHolder, i: Int) {
        holder.bind(this.rides[i])
    }
}