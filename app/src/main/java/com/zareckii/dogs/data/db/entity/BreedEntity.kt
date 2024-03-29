package com.zareckii.dogs.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "breeds")

data class BreedEntity(
    @PrimaryKey
    @ColumnInfo(name = "breed_name")
    val breedName: String
)