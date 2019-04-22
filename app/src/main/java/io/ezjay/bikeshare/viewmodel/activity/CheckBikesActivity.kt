package io.ezjay.bikeshare.viewmodel.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import io.ezjay.bikeshare.R
import io.ezjay.bikeshare.model.BikeDao
import io.ezjay.bikeshare.viewmodel.adapter.BikeListAdapter

class CheckBikesActivity : AppCompatActivity() {

    // UI
    private lateinit var bikeList : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_bikes)

        println("OMG TEST: " + BikeDao.getBikes().size)

        this.bikeList = this.findViewById<RecyclerView>(R.id.bike_list).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this.context)
            adapter = BikeListAdapter(BikeDao.getBikes())
        }
    }
}
