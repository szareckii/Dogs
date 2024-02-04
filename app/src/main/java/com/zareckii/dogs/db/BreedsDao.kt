package com.zareckii.dogs.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BreedsDao {

    @Query("SELECT * FROM breeds")
    fun getAllBreeds(): Flow<List<BreedEntity>>

    @Query("SELECT * FROM breeds WHERE breed_name = :breed")
    suspend fun getBreed(breed: String): BreedEntity

    @Query("SELECT * FROM breeds WHERE is_favorite = :isFavorite")
    fun getFavoriteBreeds(isFavorite: Boolean): Flow<List<BreedEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllBreeds(breeds: List<BreedEntity>)

}