package id.conversion.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginRequestBody(
    val PhoneNumber: String = "",
    val PIN: String = "",
    val ModelType: String = "",
    val OS: String = "",
    val ModelNumber: String = "",
    val DeviceVersion: String = "",
    val BuildNumber: String = "",
    val FCM: String = ""
) : Parcelable