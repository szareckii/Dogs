package com.zareckii.dogs.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images")

data class ImageEntity(
    @PrimaryKey
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
    @ColumnInfo(name = "breed_name")
    val breedName: String,
    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean,
    @ColumnInfo(name = "added")
    val added: Long? = null
)