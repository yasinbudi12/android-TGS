package id.conversion.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PaymentService(
    val code: String = "",
    val name: String = "",
    val res: String = "",
) : Parcelable