package io.ezjay.bikeshare.viewmodel.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout
import io.ezjay.bikeshare.R
import io.ezjay.bikeshare.viewmodel.fragment.BikeShareFragment

class BikeShareActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bike_share)

        if (this.findViewById<FrameLayout>(R.id.fragment_container) != null && savedInstanceState == null) {
            this.supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, BikeShareFragment())
                .commit()
        }
    }
}
