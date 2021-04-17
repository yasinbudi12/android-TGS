package id.conversion.database

import androidx.room.Database
import androidx.room.RoomDatabase
import id.conversion.dao.UserDao
import id.conversion.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun dao(): UserDao
}