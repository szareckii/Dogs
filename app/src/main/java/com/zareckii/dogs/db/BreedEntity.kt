package com.zareckii.dogs.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "breeds",
)

data class BreedEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "breed_name")
    val breedName: String,
    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean
)