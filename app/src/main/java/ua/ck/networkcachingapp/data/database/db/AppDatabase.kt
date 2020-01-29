package ua.ck.networkcachingapp.data.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ua.ck.networkcachingapp.data.database.config.DatabaseConfig
import ua.ck.networkcachingapp.data.database.dao.PlaceDao
import ua.ck.networkcachingapp.data.database.entity.PlaceEntity

@Database(entities = [PlaceEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    // DAO
    abstract fun placeDao(): PlaceDao

    companion object {
        @Volatile
        private var appDatabaseInstance: AppDatabase? = null
        private val lock = Any()

        operator fun invoke(context: Context): AppDatabase {
            return appDatabaseInstance ?: synchronized<AppDatabase>(lock) {
                return appDatabaseInstance ?: buildDatabase(context).also {
                    appDatabaseInstance = it
                }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DatabaseConfig.DATABASE_NAME
            ).build()
        }
    }
}