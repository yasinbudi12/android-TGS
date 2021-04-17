package id.conversion.app.di.module

import android.content.Context
import id.conversion.app.MyApplication
import id.conversion.app.shared.AppPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideContext(application: MyApplication): Context {
        return application
    }

    @Singleton
    @Provides
    fun providePreferences(context: Context): AppPreferences {
        return AppPreferences(context)
    }

}