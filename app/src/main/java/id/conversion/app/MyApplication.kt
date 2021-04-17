package id.conversion.app

import androidx.multidex.MultiDex
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import id.conversion.app.di.component.DaggerAppComponent

class MyApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

        initializeMultiDex()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this@MyApplication)
    }

    private fun initializeMultiDex() {
        MultiDex.install(this@MyApplication)
    }

}