package com.zareckii.dogs.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.zareckii.dogs.R

enum class NavigationTree {
    Breeds,
    Favorites,
    Breed,
    FavoriteImages
}

sealed class ScreenBottomNav(val route: String, @StringRes val title: Int, @DrawableRes val icon: Int) {
    data object Breeds : ScreenBottomNav(route = NavigationTree.Breeds.name, title = R.string.dogs, icon = R.drawable.ic_dog)
    data object Favorites : ScreenBottomNav(route = NavigationTree.Favorites.name, title = R.string.favorites, icon = R.drawable.ic_favourite)
}
