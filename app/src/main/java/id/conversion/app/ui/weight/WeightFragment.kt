package id.conversion.app.ui.weight

import id.conversion.app.R
import id.conversion.app.base.BaseFragment
import id.conversion.app.databinding.FragmentWeightBinding
import id.conversion.ext.observer.observe
import id.conversion.ext.property.toDoubleOrZero

class WeightFragment : BaseFragment<WeightViewModel, FragmentWeightBinding>(WeightViewModel::class.java) {

    override fun onViewCreated() {
        binding.apply {
            lifecycleOwner = this@WeightFragment
            vm = viewModel
        }
    }

    override fun layoutResources(): Int {
        return R.layout.fragment_weight
    }

    override fun setupObserver() {
        viewModel.run {
            observe(textInput) {
                val value = it.toDoubleOrZero()
                if (value > 0) {
                    textUnitH.postValue("${value * 10} hg")
                    textUnitDA.postValue("${value * 100} dag")
                    textUnitG.postValue("${value * 1000} g")
                    textUnitD.postValue("${value * 10000} dg")
                    textUnitC.postValue("${value * 100000} cg")
                    textUnitM.postValue("${value * 1000000} mg")
                } else {
                    return@observe
                }
            }
        }
    }

}