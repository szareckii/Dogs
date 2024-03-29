package com.zareckii.dogs.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zareckii.dogs.data.db.entity.BreedEntity
import com.zareckii.dogs.data.db.entity.ImageEntity

@Database(
    entities = [BreedEntity::class, ImageEntity::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    companion object {
        private const val databaseName = "breeds-db"

        fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                databaseName
            ).build()
        }
    }

    abstract fun breedsDao(): BreedsDao

    abstract fun imageDao(): ImagesDao

}
