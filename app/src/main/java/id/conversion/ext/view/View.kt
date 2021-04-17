package id.conversion.ext.view

import android.graphics.Color
import android.os.Handler
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.AnimRes
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import id.conversion.app.R
import id.conversion.ext.context.inputManager
import id.conversion.ext.context.loadAnimation

fun View.dip(value: Int): Int = (value * resources.displayMetrics.density).toInt()

fun View.slideDown() {
    animate().setDuration(600).translationY(dip(height) * 1f).start()
}

fun View.slideUp() {
    animate().setDuration(600).translationY(16f).start()
}

fun View.showKeyboard() {
    clearFocus()
    requestFocus()
    context.inputManager?.showSoftInput(this, InputMethodManager.SHOW_FORCED)
}

fun View.closeKeyboard() {
    clearFocus()
    context.inputManager?.hideSoftInputFromWindow(windowToken, InputMethodManager.RESULT_UNCHANGED_SHOWN)
}

fun View?.backgroundColor(hexCode: String) {
    this?.run { setBackgroundColor(Color.parseColor(hexCode)) }
}

fun View?.backgroundColor(resId: Int) {
    this?.run { setBackgroundColor(ContextCompat.getColor(context, resId)) }
}

fun View?.onItemClicked() {
    this?.let {
        setOnClickListener { _ ->
            isEnabled = false
            postDelayed({ isEnabled = true }, 1000)
        }
    }
}

fun View.visible(animate: Boolean = true) {
    if (!animate) {
        visibility = View.VISIBLE
        alpha = 1.0f
        return
    }

    alpha = 0f
    visibility = View.VISIBLE
    animate().alpha(1.0f).duration = 100
}

@Suppress("DEPRECATION")
fun View.invisible(delayMillis: Long = 100) {
    animate().alpha(0f).duration = delayMillis

    Handler().postDelayed({
        visibility = View.INVISIBLE
    }, delayMillis)
}

@Suppress("DEPRECATION")
fun View.gone(delayMillis: Long = 100) {
    animate().alpha(0f).duration = delayMillis

    Handler().postDelayed({
        visibility = View.GONE
    }, delayMillis)
}

fun View?.toggleView(isTrue: Boolean, isGone: Boolean = true) {
    this?.let {
        if (isTrue) {
            visible()
        } else {
            if (isGone) {
                gone()
            } else {
                invisible()
            }
        }
    }
}

fun View.revealWithAnimation(@AnimRes resId: Int = R.anim.fade_in) {
    if (!isVisible) {
        isVisible = true
        startAnimation(context.loadAnimation(resId))
    }
}

fun View.hideWithAnimation(@AnimRes resId: Int = R.anim.fade_out, invisible: Boolean = false) {
    if (isVisible) {
        startAnimation(context.loadAnimation(resId))

        if (invisible) {
            isInvisible = true
        } else {
            isVisible = false
        }
    }
}