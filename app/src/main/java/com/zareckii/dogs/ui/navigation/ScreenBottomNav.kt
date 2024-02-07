package com.zareckii.dogs.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.zareckii.dogs.R

enum class NavigationTree {
    Breeds,
    Favourites,
    Breed,
    FavoriteImages
}

sealed class ScreenBottomNav(val route: String, @StringRes val title: Int, @DrawableRes val icon: Int) {
    data object Breeds : ScreenBottomNav(route = NavigationTree.Breeds.name, title = R.string.dogs, icon = R.drawable.ic_dog)
    data object Favourites : ScreenBottomNav(route = NavigationTree.Favourites.name, title = R.string.favourites, icon = R.drawable.ic_favourite)
}
