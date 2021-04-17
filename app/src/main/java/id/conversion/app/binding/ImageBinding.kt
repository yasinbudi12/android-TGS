package id.conversion.app.binding

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import id.conversion.ext.image.loadBarcode
import id.conversion.ext.image.loadImage
import id.conversion.ext.image.loadQrCode
import id.conversion.ext.resource.getResId

object ImageBinding {

    @JvmStatic
    @BindingAdapter("bind:loadBitmap")
    fun loadBitmap(view: ImageView?, bitmap: Bitmap?) {
        bitmap?.run { view?.setImageBitmap(this) }
    }

    @JvmStatic
    @BindingAdapter("bind:loadImage")
    fun loadImageFromUrl(view: ImageView?, url: String?) {
        view?.loadImage(url)
    }

    @JvmStatic
    @BindingAdapter("bind:loadResource")
    fun loadImageResource(view: ImageView?, resName: String?) {
        if (resName == null || view == null) return
        view.run { loadImage(context.getResId(resName)) }
    }

    @JvmStatic
    @BindingAdapter("bind:loadQrCode")
    fun loadQrCode(view: ImageView, data: String?) {
        data?.apply {
            view.loadQrCode(this)
        }
    }

    @JvmStatic
    @BindingAdapter("bind:loadBarcode")
    fun loadBarcode(view: ImageView, data: String?) {
        data?.apply {
            view.loadBarcode(this)
        }
    }

}