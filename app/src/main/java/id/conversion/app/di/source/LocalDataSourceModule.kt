package id.conversion.app.di.source

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import id.conversion.database.UserDatabase
import javax.inject.Singleton

@Module
class LocalDataSourceModule {

    @Singleton
    @Provides
    fun provideUserDatabase(context: Context): UserDatabase {
        return Room.databaseBuilder(context, UserDatabase::class.java, "user.db").build()
    }

}