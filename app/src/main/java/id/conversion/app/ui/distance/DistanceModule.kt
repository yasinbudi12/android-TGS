package id.conversion.app.ui.distance

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import id.conversion.app.di.ViewModelProviderFactory

@Module
class DistanceModule {

    @Provides
    fun provideDistanceViewModel(): DistanceViewModel {
        return DistanceViewModel()
    }

    @Provides
    fun provideDistanceViewModelFactory(viewModel: DistanceViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(viewModel)
    }

}