package id.conversion.app.di.component

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import id.conversion.app.MyApplication
import id.conversion.app.di.builder.ActivityBuilder
import id.conversion.app.di.builder.FragmentBuilder
import id.conversion.app.di.module.AppModule
import id.conversion.app.di.module.NetworkModule
import id.conversion.app.di.source.LocalDataSourceModule
import id.conversion.app.di.source.RemoteDataSourceModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class, AppModule::class, NetworkModule::class,
        RemoteDataSourceModule::class, LocalDataSourceModule::class,
        ActivityBuilder::class, FragmentBuilder::class
    ]
)
interface AppComponent : AndroidInjector<MyApplication> {

    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<MyApplication>

}