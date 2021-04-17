package id.conversion.ext.widget

import android.graphics.Color
import androidx.cardview.widget.CardView
import id.conversion.ext.view.visible

fun CardView?.backgroundColor(hexCode: String) {
    this?.run {
        setCardBackgroundColor(Color.parseColor(hexCode))
        visible(false)
    }
}