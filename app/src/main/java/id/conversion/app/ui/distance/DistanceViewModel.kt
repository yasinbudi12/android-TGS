package id.conversion.app.ui.distance

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DistanceViewModel : ViewModel() {

    val textInput = MediatorLiveData<String>()

    val textUnitH = MediatorLiveData<String>()
    val textUnitDA = MediatorLiveData<String>()
    val textUnitG = MediatorLiveData<String>()
    val textUnitD = MediatorLiveData<String>()
    val textUnitC = MediatorLiveData<String>()
    val textUnitM = MediatorLiveData<String>()

}