package id.conversion.app.binding

import android.util.TypedValue
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import id.conversion.ext.view.gone
import id.conversion.ext.view.visible
import id.conversion.ext.widget.applyStrikeThrough
import id.conversion.ext.widget.textColor

object TextBinding {

    @JvmStatic
    @BindingAdapter("bindText")
    fun bindText(textView: TextView, data: String?) {
        if (data.isNullOrEmpty()) textView.gone()
        else {
            textView.apply {
                text = data
                visible(false)
            }
        }
    }

    @JvmStatic
    @BindingAdapter("bind:textColor")
    fun bindTextColor(textView: TextView, hexCode: String? = "#2c3e50") {
        if (hexCode == null) textView.textColor("#808080")
        else textView.textColor(hexCode)
    }

    @JvmStatic
    @BindingAdapter("bindTextSize")
    fun bindTextSize(textView: TextView, size: Float = 14f) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, size)
    }

    @JvmStatic
    @BindingAdapter("enableStrikeThrough")
    fun setStrikeThrough(textView: TextView, enabled: Boolean = false) {
        if (enabled) textView.applyStrikeThrough()
    }

    @JvmStatic
    @BindingAdapter("htmlText")
    fun setHtmlText(textView: TextView, data: String?) {
        textView.text = data?.let { HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY) }
    }

}