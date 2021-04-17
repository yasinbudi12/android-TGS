package id.conversion.app.di.module

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import id.conversion.ext.common.applyIf
import id.conversion.interceptor.AppInterceptor
import dagger.Module
import dagger.Provides
import id.conversion.app.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    companion object {
        private const val REQUEST_TIMEOUT = 30L
    }

    @Singleton
    @Provides
    fun provideChuckInterceptor(context: Context): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context)
            .collector(ChuckerCollector(context))
            .maxContentLength(250000L)
            .redactHeaders(emptySet())
            .alwaysReadResponseBody(false)
            .build()
    }

    @Singleton
    @Provides
    fun provideHttpClient(chuckInterceptor: ChuckerInterceptor): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(AppInterceptor())
            .addInterceptor(httpLoggingInterceptor)
            .applyIf(BuildConfig.DEBUG) { addInterceptor(chuckInterceptor) }
            .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): Converter.Factory {
        val builder = GsonBuilder()
            .setLenient()
            .create()

        return GsonConverterFactory.create(builder)
    }

    @Singleton
    @Provides
    fun provideRetrofit(httpClient: OkHttpClient, converter: Converter.Factory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.URL_API)
            .client(httpClient)
            .addConverterFactory(converter)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

}