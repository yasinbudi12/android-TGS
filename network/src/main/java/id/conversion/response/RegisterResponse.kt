package id.conversion.response

import android.os.Parcelable
import id.conversion.model.UserProfile
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegisterResponse(val _id: String, val PhoneNumber: String, val Profile: UserProfile) : Parcelable