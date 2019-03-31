package io.ezjay.bikeshare.viewmodel.activity

import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import io.ezjay.bikeshare.R
import io.ezjay.bikeshare.model.BikeshareDao
import io.ezjay.bikeshare.model.Ride
import io.ezjay.bikeshare.util.PictureUtils

class StartRideActivity : AppCompatActivity() {

    // UI
    private lateinit var addRide : Button
    private lateinit var pictureButton : Button
    private lateinit var header : TextView
    private lateinit var bikeName : TextView
    private lateinit var location : TextView
    private lateinit var imageView : ImageView

    private var bikeImage : Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_ride)

        this.addRide = this.findViewById(R.id.add_button)
        this.pictureButton = this.findViewById(R.id.picture_button)
        this.header = this.findViewById(R.id.header)
        this.bikeName = this.findViewById(R.id.what_text)
        this.location = this.findViewById(R.id.where_text)
        this.imageView = this.findViewById(R.id.image_view)

        this.pictureButton.setOnClickListener {
            this.dispatchTakePictureIntent()
        }

        this.addRide.setOnClickListener {
            if (!isEmpty(this.bikeName) && !isEmpty(this.location) && this.bikeImage != null) {
                val ride = Ride(
                    bikeName = this.bikeName.text.toString(),
                    startLocation = this.location.text.toString(),
                    startTime = Ride.getCurrentFormattedDateTime(),
                    bikePicture = PictureUtils.bitmapToByteArray(this.bikeImage as Bitmap),
                    active = true
                )

                BikeshareDao.addRide(ride)

                this.updateUI()
            }
        }

    }

    private val REQUEST_IMAGE_CAPTURE = 1

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
            this.imageView.setImageBitmap(this.bikeImage)
        }
    }

    private fun updateUI() {
        this.header.text = "Ride started, go get 'em!"
        this.addRide.isEnabled = false
    }

    private fun isEmpty(text: TextView): Boolean {
        return text.text.toString().isEmpty()
    }
}
