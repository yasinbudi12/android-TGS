package id.conversion.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(val name: String = "", val description: String = "") : Parcelable