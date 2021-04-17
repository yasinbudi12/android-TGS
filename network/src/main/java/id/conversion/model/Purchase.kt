package id.conversion.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Purchase(
    val code: String = "",
    val customerInfo: List<Information> = ArrayList(),
    val detail: List<Information> = ArrayList(),
    val product: Product? = null,
    val grandTotal: Int = 0
) : Parcelable