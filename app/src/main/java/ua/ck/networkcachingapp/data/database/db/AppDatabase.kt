package ua.ck.networkcachingapp.data.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import ua.ck.networkcachingapp.data.database.config.DatabaseConfig
import ua.ck.networkcachingapp.data.database.dao.PlaceDao
import ua.ck.networkcachingapp.data.database.entity.place.PlaceEntity

@Database(entities = [PlaceEntity::class], version = DatabaseConfig.DATABASE_VERSION)
abstract class AppDatabase : RoomDatabase() {

    // DAO
    abstract fun placeDao(): PlaceDao

    companion object {
        @Volatile
        private var appDatabaseInstance: AppDatabase? = null
        private val lock = Any()

        // Migration:
        // https://developer.android.com/training/data-storage/room/migrating-db-versions
        // https://medium.com/androiddevelopers/understanding-migrations-with-room-f01e04b07929
        private val migrationFrom1to2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
            }
        }

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
            )
                // Without migration: clear database
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}