package io.ezjay.bikeshare.viewmodel.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import io.ezjay.bikeshare.viewmodel.activity.EndRideActivity
import io.ezjay.bikeshare.R
import io.ezjay.bikeshare.model.BikeshareDao
import io.ezjay.bikeshare.viewmodel.activity.StartRideActivity
import io.ezjay.bikeshare.viewmodel.adapter.RideArrayAdapter

class BikeShareFragment : Fragment() {
    // UI
    private lateinit var addRide : Button
    private lateinit var endRide : Button
    private lateinit var listRides : Button
    private lateinit var list : RecyclerView

    private var showList = false

    companion object {
        private const val SHOW_LIST = "showList"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bike_share, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        this.addRide = view.findViewById(R.id.add_button)
        this.endRide = view.findViewById(R.id.end_button)
        this.listRides = view.findViewById(R.id.list_button)

        this.list = view.findViewById(R.id.ride_list)
        this.list.layoutManager = LinearLayoutManager(this.context)

        this.registerEventListeners()

        if (savedInstanceState != null) {
            this.showList = savedInstanceState[SHOW_LIST] as Boolean
            this.handleListVisibility()
        }
    }

    override fun onStart() {
        super.onStart()
        if (this.view?.context != null) {
            val hasActiveRide = BikeshareDao.getActiveRide() != null
            if (hasActiveRide) {
                this.endRide.isEnabled = true
                this.addRide.isEnabled = false
            }
            else {
                this.endRide.isEnabled = false
                this.addRide.isEnabled = true
            }

            this.list.adapter = RideArrayAdapter(BikeshareDao.getDoneRides())
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(SHOW_LIST, this.showList)
        super.onSaveInstanceState(outState)
    }

    private fun registerEventListeners() {
        this.addRide.setOnClickListener {
            val intent = Intent(this.activity,  StartRideActivity::class.java)
            this.startActivity(intent)
        }

        this.endRide.setOnClickListener {
            val intent = Intent(this.activity, EndRideActivity::class.java)
            this.startActivity(intent)
        }

        this.listRides.setOnClickListener {
            this.showList = !this.showList
            this.handleListVisibility()
        }
    }

    private fun handleListVisibility() {
        if (this.showList)
            this.list.visibility = View.VISIBLE
        else
            this.list.visibility = View.GONE
    }
}
