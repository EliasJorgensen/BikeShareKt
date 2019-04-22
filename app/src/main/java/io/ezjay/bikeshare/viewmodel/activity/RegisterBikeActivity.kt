package io.ezjay.bikeshare.viewmodel.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import io.ezjay.bikeshare.R
import io.ezjay.bikeshare.viewmodel.util.GpsManager

class RegisterBikeActivity : AppCompatActivity() {

    // UI
    private lateinit var bikePictureView : ImageView
    private lateinit var bikeName : TextView
    private lateinit var bikeType : TextView
    private lateinit var bikeLocation : TextView
    private lateinit var bikeHourlyPrice : TextView

    private lateinit var pictureButton : Button
    private lateinit var registerBikeButton : Button

    private val REQUEST_IMAGE_CAPTURE = 1
    private var bikeImage : Bitmap? = null

    private lateinit var gpsManager : GpsManager

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_register_bike)

        this.bikePictureView = this.findViewById(R.id.image_view)
        this.bikeName = this.findViewById(R.id.bike_name)
        this.bikeType = this.findViewById(R.id.bike_type)
        this.bikeLocation = this.findViewById(R.id.bike_location)
        this.bikeHourlyPrice = this.findViewById(R.id.bike_hourlyPrice)

        this.pictureButton = this.findViewById(R.id.picture_button)
        this.registerBikeButton = this.findViewById(R.id.add_button)

        this.pictureButton.setOnClickListener {
            this.dispatchTakePictureIntent()
        }

        this.gpsManager = GpsManager(this)

        this.gpsManager.requestLocationUpdates()
        this.bikeLocation.text = "Lat: ${this.gpsManager.currentLocation?.latitude}, Lon: ${this.gpsManager.currentLocation?.longitude}"
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            this.bikeImage = Bitmap.createScaledBitmap(
                imageBitmap,
                imageBitmap.width * 3,
                imageBitmap.height * 3,
                true
            )
            this.bikePictureView.setImageBitmap(this.bikeImage)
        }
    }
}
