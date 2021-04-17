package id.conversion.app.ui.weight

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import id.conversion.app.di.ViewModelProviderFactory

@Module
class WeightModule {

    @Provides
    fun provideWeightViewModel(): WeightViewModel {
        return WeightViewModel()
    }

    @Provides
    fun provideWeightViewModelFactory(viewModel: WeightViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(viewModel)
    }

}