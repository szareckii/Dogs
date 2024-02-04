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

    @Query("UPDATE images SET is_favorite = :isFavorite WHERE image_url = :imageUrl")
    fun updateImageFavorite(imageUrl: String, isFavorite: Boolean)

    @Query("SELECT * FROM images WHERE image_url = :imageUrl")
    suspend fun getImage(imageUrl: String): ImageEntity

}