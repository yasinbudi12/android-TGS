package id.conversion.app.ui.time

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel

class TimeViewModel : ViewModel() {

    val textInput = MediatorLiveData<String>()

    val textUnitMonth = MediatorLiveData<String>()
    val textUnitWeek = MediatorLiveData<String>()
    val textUnitDay = MediatorLiveData<String>()
    val textUnitHour = MediatorLiveData<String>()

}