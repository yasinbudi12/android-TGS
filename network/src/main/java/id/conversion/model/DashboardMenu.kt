package id.conversion.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DashboardMenu(val section: String = "", val menu: List<PaymentService> = ArrayList()) : Parcelable