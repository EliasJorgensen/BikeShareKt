package io.ezjay.bikeshare.util

import android.graphics.Bitmap
import java.io.ByteArrayOutputStream

object PictureUtils {
    fun bitmapToByteArray(image : Bitmap) : ByteArray {
        val stream = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }
}