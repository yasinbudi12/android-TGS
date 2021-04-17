package id.conversion.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Nominal(val label: String = "", val amount: Int = 0) : Parcelable