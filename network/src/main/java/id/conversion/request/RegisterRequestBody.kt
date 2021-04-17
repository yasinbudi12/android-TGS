package id.conversion.request

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegisterRequestBody(
    @SerializedName("FirstName") val firstName: String = "",
    @SerializedName("MiddleName") val lastName: String = "",
    @SerializedName("Email") val email: String = "",
    @SerializedName("PhoneNumber") val phoneNumber: String = "",
    @SerializedName("PIN") val pinCode: String = "",
) : Parcelable