package com.zareckii.dogs.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zareckii.dogs.data.db.entity.BreedEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BreedsDao {

    @Query("SELECT * FROM breeds ORDER BY breed_name ASC")
    fun getAllBreeds(): Flow<List<BreedEntity>>

    @Query("SELECT * FROM breeds WHERE breed_name = :breed")
    suspend fun getBreed(breed: String): BreedEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllBreeds(breeds: List<BreedEntity>)

    @Query("SELECT * FROM breeds WHERE LOWER(breed_name) LIKE  '%' || :breed || '%' ORDER BY breed_name ASC")
    suspend fun searchBreedAsc(breed: String): List<BreedEntity>

    @Query("SELECT * FROM breeds WHERE LOWER(breed_name) LIKE  '%' || :breed || '%' ORDER BY breed_name DESC")
    suspend fun searchBreedDesc(breed: String): List<BreedEntity>

}