package id.conversion.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataPlan(
    val id: Long = 0,
    val plan: String = "",
    val description: String = "",
    val price: String = ""
) : Parcelable