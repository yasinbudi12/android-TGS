package id.conversion.app.ui.currency

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import id.conversion.app.di.ViewModelProviderFactory

@Module
class CurrencyModule {

    @Provides
    fun provideCurrencyViewModel(): CurrencyViewModel {
        return CurrencyViewModel()
    }

    @Provides
    fun provideCurrencyViewModelFactory(viewModel: CurrencyViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(viewModel)
    }

}