package id.conversion.app.ui.volume

import id.conversion.app.R
import id.conversion.app.base.BaseFragment
import id.conversion.app.databinding.FragmentVolumeBinding
import id.conversion.ext.observer.observe
import id.conversion.ext.property.toDoubleOrZero

class VolumeFragment : BaseFragment<VolumeViewModel, FragmentVolumeBinding>(VolumeViewModel::class.java) {

    override fun onViewCreated() {
        binding.apply {
            lifecycleOwner = this@VolumeFragment
            vm = viewModel
        }
    }

    override fun layoutResources(): Int {
        return R.layout.fragment_volume
    }

    override fun setupObserver() {
        viewModel.run {
            observe(textInput) {
                val value = it.toDoubleOrZero()
                if (value > 0) {
                    textUnitH.postValue("${value * 10} hl")
                    textUnitDA.postValue("${value * 100} dal")
                    textUnitG.postValue("${value * 1000} l")
                    textUnitD.postValue("${value * 10000} dl")
                    textUnitC.postValue("${value * 100000} cl")
                    textUnitM.postValue("${value * 1000000} ml")
                } else {
                    return@observe
                }
            }
        }
    }

}