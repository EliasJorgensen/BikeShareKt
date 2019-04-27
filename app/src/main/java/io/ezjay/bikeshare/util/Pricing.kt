package io.ezjay.bikeshare.util

import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*
import java.util.concurrent.TimeUnit

object Pricing {
    fun getPriceFromUsage(from: Date, to: Date, pricePerHour: Float) : Float {
        return BigDecimal.valueOf(TimeUnit.MILLISECONDS.toSeconds(to.time - from.time))
            .divide(BigDecimal(60), 2, RoundingMode.HALF_UP) // Minutes
            .divide(BigDecimal(60), 2, RoundingMode.HALF_UP) // Hours
            .multiply(BigDecimal(pricePerHour.toString()))
            .toFloat()
    }
}