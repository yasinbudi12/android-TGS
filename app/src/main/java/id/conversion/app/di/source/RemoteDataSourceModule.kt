package id.conversion.app.di.source

import dagger.Module
import dagger.Provides
import id.conversion.services.AuthServices
import id.conversion.services.UserServices
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RemoteDataSourceModule {

    @Singleton
    @Provides
    fun provideAuthServices(retrofit: Retrofit): AuthServices {
        return retrofit.create(AuthServices::class.java)
    }

    @Singleton
    @Provides
    fun provideUserServices(retrofit: Retrofit): UserServices {
        return retrofit.create(UserServices::class.java)
    }

}