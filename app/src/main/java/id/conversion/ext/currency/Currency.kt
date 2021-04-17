package id.conversion.ext.currency

import id.conversion.ext.property.orZero
import java.text.NumberFormat
import java.util.*

fun Double?.format(): String {
    return try {
        NumberFormat.getNumberInstance(Locale.US).format(orZero()).replace(",", ".")
    } catch (e: Exception) {
        "0"
    }
}

fun Int?.toRupiah(): String {
    return try {
        "Rp ${NumberFormat.getNumberInstance(Locale.US).format(orZero()).replace(",", ".")}"
    } catch (e: Exception) {
        "Rp 0"
    }
}