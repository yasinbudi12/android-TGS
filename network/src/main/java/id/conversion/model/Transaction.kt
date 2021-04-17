package id.conversion.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Transaction(
    val name: String = "",
    val type: String = "",
    val time: String = "",
    val amount: String = "",
    val complete: Boolean = false
) : Parcelable