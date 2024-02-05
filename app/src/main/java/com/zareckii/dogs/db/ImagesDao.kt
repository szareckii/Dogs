package com.zareckii.dogs.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ImagesDao {

    @Query("SELECT * FROM images WHERE breed_name = :breed")
    fun getImages(breed: String): Flow<List<ImageEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllImages(images: List<ImageEntity>)

    @Query("UPDATE images SET is_favorite = :isFavorite, added = :added WHERE image_url = :imageUrl")
    fun updateImageFavorite(imageUrl: String, isFavorite: Boolean, added: Long?)

    @Query("SELECT * FROM images WHERE image_url = :imageUrl")
    suspend fun getImage(imageUrl: String): ImageEntity

    @Query("SELECT breed_name, image_url, MAX(added) FROM images WHERE is_favorite = 1 GROUP BY breed_name")
    fun getFavoriteBreeds(): Flow<List<ImageLastFavorite>>

    @Query("SELECT image_url FROM images WHERE is_favorite = 1 and breed_name = :breed")
    fun getFavoriteImages(breed: String): Flow<List<String>>

}