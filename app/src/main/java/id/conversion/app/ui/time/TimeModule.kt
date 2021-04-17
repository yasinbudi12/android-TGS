package id.conversion.app.ui.time

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import id.conversion.app.di.ViewModelProviderFactory

@Module
class TimeModule {

    @Provides
    fun provideTimeViewModel(): TimeViewModel {
        return TimeViewModel()
    }

    @Provides
    fun provideTimeViewModelFactory(viewModel: TimeViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(viewModel)
    }

}