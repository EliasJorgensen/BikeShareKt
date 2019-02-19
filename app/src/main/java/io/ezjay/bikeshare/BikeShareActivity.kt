package io.ezjay.bikeshare

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class BikeShareActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bike_share)

        val fm = this.supportFragmentManager
        fm.beginTransaction()
            .replace(R.id.fragment_container, BikeShareFragment())
            .commit()
    }
}
