package id.conversion.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileRequestBody(val PhoneNumber: String = "") : Parcelable