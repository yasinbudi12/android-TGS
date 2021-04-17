package id.conversion.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Payment(
    val id: Int = 0,
    val section: String? = null,
    @SerializedName("payment_method") val method: String? = null,
    @SerializedName("virtual_account") val virtualAccount: String? = null,
    @SerializedName("account_number") val accountNumber: String? = null,
    val res: String? = null
) : Parcelable {
    fun number(): String = if (accountNumber.isNullOrEmpty()) virtualAccount.orEmpty() else accountNumber
}