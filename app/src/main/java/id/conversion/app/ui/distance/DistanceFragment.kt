package id.conversion.app.ui.distance

import id.conversion.app.R
import id.conversion.app.base.BaseFragment
import id.conversion.app.databinding.FragmentDistanceBinding
import id.conversion.ext.observer.observe
import id.conversion.ext.property.toDoubleOrZero

class DistanceFragment : BaseFragment<DistanceViewModel, FragmentDistanceBinding>(DistanceViewModel::class.java) {

    override fun onViewCreated() {
        binding.apply {
            lifecycleOwner = this@DistanceFragment
            vm = viewModel
        }
    }

    override fun layoutResources(): Int {
        return R.layout.fragment_distance
    }

    override fun setupObserver() {
        viewModel.run {
            observe(textInput) {
                val value = it.toDoubleOrZero()
                if (value > 0) {
                    textUnitH.postValue("${value * 10} hm")
                    textUnitDA.postValue("${value * 100} dam")
                    textUnitG.postValue("${value * 1000} m")
                    textUnitD.postValue("${value * 10000} dm")
                    textUnitC.postValue("${value * 100000} cm")
                    textUnitM.postValue("${value * 1000000} mm")
                } else {
                    return@observe
                }
            }
        }
    }

}