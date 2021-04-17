package id.conversion.ext.fragment

import androidx.fragment.app.Fragment
import id.conversion.ext.property.orFalse
import id.conversion.ext.property.orZero

fun Fragment.getStringArgs(key: String): String {
    return arguments?.getString(key, "").orEmpty()
}

fun Fragment.getBooleanArgs(key: String): Boolean {
    return arguments?.getBoolean(key, false).orFalse()
}

fun Fragment.getIntArgs(key: String): Int {
    return arguments?.getInt(key, 0).orZero()
}

@Suppress("UNCHECKED_CAST")
fun <T : Any> Fragment.getDataArgs(key: String): T? {
    arguments?.get(key)?.let { return it as T }
    return null
}