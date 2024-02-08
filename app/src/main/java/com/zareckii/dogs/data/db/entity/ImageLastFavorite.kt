package com.zareckii.dogs.data.db.entity

import androidx.room.ColumnInfo

data class ImageLastFavorite(
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
    @ColumnInfo(name = "breed_name")
    val breedName: String
)