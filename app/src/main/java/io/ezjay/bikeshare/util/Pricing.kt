package io.ezjay.bikeshare.util

import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*
import java.util.concurrent.TimeUnit

object Pricing {
    fun getPriceFromUsage(from: Date, to: Date, pricePerHour: Float) : Float {
        return BigDecimal.valueOf(TimeUnit.MILLISECONDS.toSeconds(to.time - from.time))
            .divide(BigDecimal(60))
            .setScale(2, RoundingMode.UP)
            .multiply(BigDecimal(pricePerHour.toString()))
            .toFloat()
    }
}