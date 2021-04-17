package id.conversion.app.ui.volume

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel

class VolumeViewModel : ViewModel() {

    val textInput = MediatorLiveData<String>()

    val textUnitH = MediatorLiveData<String>()
    val textUnitDA = MediatorLiveData<String>()
    val textUnitG = MediatorLiveData<String>()
    val textUnitD = MediatorLiveData<String>()
    val textUnitC = MediatorLiveData<String>()
    val textUnitM = MediatorLiveData<String>()

}