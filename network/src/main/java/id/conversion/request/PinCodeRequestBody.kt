package id.conversion.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PinCodeRequestBody(
    val PhoneNumber: String = "",
    val PIN: String = ""
) : Parcelable