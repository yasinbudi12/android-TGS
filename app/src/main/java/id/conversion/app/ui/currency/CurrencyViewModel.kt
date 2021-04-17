package id.conversion.app.ui.currency

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel

class CurrencyViewModel : ViewModel() {

    val textInput = MediatorLiveData<String>()

    val textRupiah = MediatorLiveData<String>()
    val textRinggit = MediatorLiveData<String>()
    val textWon = MediatorLiveData<String>()
    val textYen = MediatorLiveData<String>()
    val textEuro = MediatorLiveData<String>()

}