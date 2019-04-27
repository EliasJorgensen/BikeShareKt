package io.ezjay.bikeshare.viewmodel.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.location.Location
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import io.ezjay.bikeshare.R
import io.ezjay.bikeshare.model.Bike
import io.ezjay.bikeshare.model.BikeDao
import io.ezjay.bikeshare.util.PictureUtils
import io.ezjay.bikeshare.util.GpsManager

class RegisterBikeActivity : AppCompatActivity() {

    // UI
    private lateinit var bikePictureView : ImageView
    private lateinit var header : TextView
    private lateinit var bikeName : TextView
    private lateinit var bikeType : TextView
    private lateinit var bikeLocation : TextView
    private lateinit var bikeHourlyPrice : TextView

    private lateinit var pictureButton : Button
    private lateinit var registerBikeButton : Button

    private val REQUEST_IMAGE_CAPTURE = 1
    private var bikeImage : Bitmap? = null

    private lateinit var gpsManager : GpsManager

    private var isRegisterBikeButtonEnabled = true
    private val registeredBikeHeaderText = "Bike registered!"

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_register_bike)

        this.bikePictureView = this.findViewById(R.id.image_view)
        this.header = this.findViewById(R.id.header)
        this.bikeName = this.findViewById(R.id.bike_name)
        this.bikeType = this.findViewById(R.id.bike_type)
        this.bikeLocation = this.findViewById(R.id.bike_location)
        this.bikeHourlyPrice = this.findViewById(R.id.bike_hourlyPrice)

        this.pictureButton = this.findViewById(R.id.picture_button)
        this.registerBikeButton = this.findViewById(R.id.add_button)

        this.pictureButton.setOnClickListener {
            this.dispatchTakePictureIntent()
        }

        this.registerBikeButton.setOnClickListener {
            this.registerBike()
        }

        this.gpsManager = GpsManager(this)

        this.gpsManager.requestLocationUpdates()
        this.bikeLocation.text = GpsManager.locationToString(this.gpsManager.currentLocation)

        if (savedInstanceState != null) {
            this.isRegisterBikeButtonEnabled = savedInstanceState.getBoolean("isRegisterBikeButtonEnabled")
            this.registerBikeButton.isEnabled = this.isRegisterBikeButtonEnabled
            savedInstanceState.getByteArray("bikeImage")?.let {
                this.bikeImage = PictureUtils.byteArrayToBitmap(it)
                this.bikePictureView.setImageBitmap(this.bikeImage)
            }
            if (!this.isRegisterBikeButtonEnabled)
                this.header.text = this.registeredBikeHeaderText
        }
    }

    private fun registerBike() {
        if (!this.validateFields()) {
            Toast.makeText(this.applicationContext, "Please ensure that all fields are correct", Toast.LENGTH_SHORT).show()
            return
        }

        BikeDao.addBike(Bike(
            name = this.bikeName.text.toString(),
            type = this.bikeType.text.toString(),
            location = GpsManager.locationToString(this.gpsManager.currentLocation),
            hourlyPrice = this.bikeHourlyPrice.text.toString().toFloatOrNull(),
            picture = PictureUtils.bitmapToByteArray(this.bikeImage as Bitmap),
            available = true
        ))

        this.header.text = this.registeredBikeHeaderText
        this.isRegisterBikeButtonEnabled = false
        this.registerBikeButton.isEnabled = this.isRegisterBikeButtonEnabled
    }

    private fun validateFields() : Boolean {
        if (this.bikeName.text.isBlank()) return false
        if (this.bikeType.text.isBlank()) return false
        if (this.bikeHourlyPrice.text.isBlank() || this.bikeHourlyPrice.text.toString().toFloatOrNull() == null) return false
        if (this.bikeImage == null) return false
        return true
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
                imageBitmap.width * 2,
                imageBitmap.height * 2,
                true
            )
            this.bikePictureView.setImageBitmap(this.bikeImage)
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putBoolean("isRegisterBikeButtonEnabled", this.isRegisterBikeButtonEnabled)
        this.bikeImage?.let { outState?.putByteArray("bikeImage", PictureUtils.bitmapToByteArray(it)) }
        super.onSaveInstanceState(outState)
    }
}
