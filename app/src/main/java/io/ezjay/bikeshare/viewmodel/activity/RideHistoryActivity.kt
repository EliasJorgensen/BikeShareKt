package io.ezjay.bikeshare.viewmodel.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import io.ezjay.bikeshare.R
import io.ezjay.bikeshare.model.RideDao
import io.ezjay.bikeshare.viewmodel.adapter.RideListAdapter

class RideHistoryActivity : AppCompatActivity() {

    private val rideList = RideDao.getDoneRides()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ride_history)

        if (rideList.isNotEmpty()) {
            this.findViewById<RecyclerView>(R.id.ride_list).also {
                it.setHasFixedSize(true)
                it.layoutManager = LinearLayoutManager(it.context)
                it.adapter = RideListAdapter(this.rideList)
            }
        } else {
            this.findViewById<TextView>(R.id.no_bikes).apply {
                visibility = View.VISIBLE
            }
        }
    }
}
