package id.conversion.app.ui.currency

import id.conversion.app.R
import id.conversion.app.base.BaseFragment
import id.conversion.app.databinding.FragmentCurrencyBinding
import id.conversion.ext.currency.format
import id.conversion.ext.observer.observe
import id.conversion.ext.property.toDoubleOrZero

class CurrencyFragment : BaseFragment<CurrencyViewModel, FragmentCurrencyBinding>(CurrencyViewModel::class.java) {

    override fun onViewCreated() {
        binding.apply {
            lifecycleOwner = this@CurrencyFragment
            vm = viewModel
        }
    }

    override fun layoutResources(): Int {
        return R.layout.fragment_currency
    }

    override fun setupObserver() {
        viewModel.run {
            observe(textInput) {
                val value = it.toDoubleOrZero()
                if (value > 0) {
                    textRupiah.postValue("${(value * 14565.0).format()} Rupiah")
                    textRinggit.postValue("${(value * 4.1260).format()} Ringgit")
                    textWon.postValue("${(value * 1115.14).format()} Won")
                    textYen.postValue("${(value * 108.80).format()} Yen")
                    textEuro.postValue("${(value * 0.8347).format()} Euro")
                } else {
                    return@observe
                }
            }
        }
    }

}