package id.conversion.ext.navigation

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.navigation.NavController

fun NavController?.navigateTo(resId: Int) {
    this?.currentDestination?.let { destination ->
        if (destination.id == resId) return
        if (destination.parent?.id == destination.getAction(resId)?.destinationId) return
        navigate(resId)
    }
}

fun NavController?.navigateTo(resId: Int, argsName: String, data: Any?) {
    val navBundle: Bundle = bundleOf(argsName to data)

    this?.currentDestination?.let { destination ->
        if (destination.id == resId) return
        if (destination.id == destination.getAction(resId)?.destinationId) return
        navigate(resId, navBundle)
    }
}

fun NavController?.navigateTo(resId: Int, bundle: Bundle) {
    this?.currentDestination?.let { destination ->
        if (destination.id == resId) return
        if (destination.id == destination.getAction(resId)?.destinationId) return
        navigate(resId, bundle)
    }
}

fun NavController?.navigateMenu(resId: Int): Boolean {
    this?.currentDestination?.let { destination ->
        if (destination.id == resId) return false
        if (destination.parent?.id == destination.getAction(resId)?.destinationId) return false
        navigate(resId)
    }
    return true
}