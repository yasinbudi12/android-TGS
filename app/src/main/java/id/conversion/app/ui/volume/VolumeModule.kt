package id.conversion.app.ui.volume

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import id.conversion.app.di.ViewModelProviderFactory

@Module
class VolumeModule {

    @Provides
    fun provideSlideshowViewModel(): VolumeViewModel {
        return VolumeViewModel()
    }

    @Provides
    fun provideSlideshowViewModelFactory(viewModel: VolumeViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(viewModel)
    }

}