package id.conversion.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Banner(val imageUrl: String, val content: String) : Parcelable