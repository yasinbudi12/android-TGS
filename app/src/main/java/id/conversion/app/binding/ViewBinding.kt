package id.conversion.app.binding

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import id.conversion.ext.property.orFalse
import id.conversion.ext.view.backgroundColor
import id.conversion.ext.view.gone
import id.conversion.ext.view.invisible
import id.conversion.ext.view.visible

object ViewBinding {

    @JvmStatic
    @BindingAdapter("backgroundColor")
    fun setBackgroundColor(view: View, hexCode: String? = "#ffffff") {
        hexCode?.let { view.backgroundColor(it) }
    }

    @JvmStatic
    @BindingAdapter("visibility")
    fun setVisibility(view: View, visibility: Int) {
        when (visibility) {
            View.VISIBLE -> view.visible()
            View.GONE -> view.gone()
            View.INVISIBLE -> view.invisible()
        }
    }

    @JvmStatic
    @BindingAdapter("refreshing")
    fun setRefreshing(view: SwipeRefreshLayout, refreshing: Boolean?) {
        view.isRefreshing = refreshing.orFalse()
    }

}