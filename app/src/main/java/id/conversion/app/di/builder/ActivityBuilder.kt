package id.conversion.app.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.conversion.app.ui.main.MainActivity
import id.conversion.app.ui.main.MainModule

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun contributeAuthorizationActivity(): MainActivity

}