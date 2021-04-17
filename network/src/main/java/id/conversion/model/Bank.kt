package id.conversion.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Bank(
    val id: Int = 0,
    @SerializedName("bankName") val bankName: String? = null,
    val res: String? = null
) : Parcelable