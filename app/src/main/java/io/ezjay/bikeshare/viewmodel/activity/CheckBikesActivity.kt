package io.ezjay.bikeshare.viewmodel.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import io.ezjay.bikeshare.R
import io.ezjay.bikeshare.model.BikeDao
import io.ezjay.bikeshare.viewmodel.adapter.BikeListAdapter

class CheckBikesActivity : AppCompatActivity() {

    private val bikeList = BikeDao.getBikes()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_bikes)

        if (bikeList.isNotEmpty()) {
            this.findViewById<RecyclerView>(R.id.bike_list).also {
                it.setHasFixedSize(true)
                it.layoutManager = LinearLayoutManager(it.context)
                it.adapter = BikeListAdapter(this.bikeList)
            }
        } else {
            this.findViewById<TextView>(R.id.no_bikes).apply {
                visibility = View.VISIBLE
            }
        }
    }
}
