package com.example.compose_ui.ui.extensions

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat

fun Double?.roundNumber(decimalPlace: Int): Double {
    return try {
        var bd = BigDecimal(this.toString())
        bd = bd.setScale(decimalPlace, RoundingMode.HALF_EVEN)
        return bd.toDouble()
    } catch (_: Exception) {
        0.00
    }
}

fun Double.convertToDoubleDisplay(
    isCurrency: Boolean = false,
    decimalPlace: Int = 2
): String {
    return try {
        val numberFormat = toBigDecimal().setScale(decimalPlace, RoundingMode.HALF_UP)
        val decimalFormat = DecimalFormat("#,##0.00")
        "${if (isCurrency) "$" else ""}${decimalFormat.format(numberFormat)}"
    } catch (_: Exception) {
        ""
    }
}