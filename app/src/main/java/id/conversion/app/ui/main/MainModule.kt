package id.conversion.app.ui.main

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import id.conversion.app.di.ViewModelProviderFactory

@Module
class MainModule {

    @Provides
    fun provideMainViewModel(): MainViewModel {
        return MainViewModel()
    }

    @Provides
    fun provideMainViewModelFactory(viewModel: MainViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(viewModel)
    }

}