package id.conversion.ext.image

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.journeyapps.barcodescanner.BarcodeEncoder
import id.conversion.app.R
import id.conversion.ext.resource.getDrawableResource

fun ImageView.loadImage(url: String?) {
    if (url != null && url.isEmpty()) return
    Glide.with(this)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .skipMemoryCache(true)
        .placeholder(R.color.colorMediumGray)
        .error(R.color.colorMediumGray)
        .into(this)
}

fun ImageView.loadImage(@DrawableRes resId: Int?) {
    if (resId == null) return
    Glide.with(this)
        .load(context.getDrawableResource(resId))
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .placeholder(R.color.colorMediumGray)
        .error(R.color.colorMediumGray)
        .into(this)
}

fun ImageView?.loadQrCode(data: String) {
    if (this == null) return
    try {
        setImageBitmap(BarcodeEncoder().encodeBitmap(data, BarcodeFormat.QR_CODE, 400, 400))
    } catch (e: WriterException) {
        e.printStackTrace()
    }
}

fun ImageView?.loadBarcode(data: String) {
    if (this == null) return
    try {
        setImageBitmap(BarcodeEncoder().encodeBitmap(data, BarcodeFormat.EAN_13, 600, 400))
    } catch (e: WriterException) {
        e.printStackTrace()
    }
}