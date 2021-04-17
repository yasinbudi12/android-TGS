package id.conversion.app.ui.time

import id.conversion.app.R
import id.conversion.app.base.BaseFragment
import id.conversion.app.databinding.FragmentTimeBinding
import id.conversion.ext.observer.observe
import id.conversion.ext.property.toIntOrZero

class TimeFragment : BaseFragment<TimeViewModel, FragmentTimeBinding>(TimeViewModel::class.java) {

    override fun onViewCreated() {
        binding.apply {
            lifecycleOwner = this@TimeFragment
            vm = viewModel
        }
    }

    override fun layoutResources(): Int {
        return R.layout.fragment_time
    }

    override fun setupObserver() {
        viewModel.run {
            observe(textInput) {
                val value = it.toIntOrZero()
                if (value > 0) {
                    textUnitMonth.postValue("${value * 12} Bulan")
                    textUnitWeek.postValue("${value * 52} Minggu")
                    textUnitDay.postValue("${value * 365} Hari")
                    textUnitHour.postValue("${value * 8760} Jam")
                } else {
                    return@observe
                }
            }
        }
    }

}