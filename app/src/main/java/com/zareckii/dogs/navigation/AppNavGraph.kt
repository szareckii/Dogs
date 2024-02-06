package com.zareckii.dogs.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.zareckii.dogs.ui.breeddetail.BreedDetailScreen
import com.zareckii.dogs.ui.breeds.BreedsScreen
import com.zareckii.dogs.ui.favouriteimages.FavoriteImagesScreen
import com.zareckii.dogs.ui.favourites.FavoritesScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues
) {
    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding()
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = ScreenBottomNav.Breeds.route,
            modifier = modifier.padding(padding)
        ) {
            composable(NavigationTree.Breeds.name) {
                BreedsScreen(
                    innerPadding = innerPadding,
                    onClickBread = { bread ->
                        navController.navigate(NavigationTree.Breed.name + "/$bread")
                    }
                )
            }
            composable(NavigationTree.Favourites.name) {
                FavoritesScreen(innerPadding = innerPadding,
                    onClickBreed = { bread ->
                        navController.navigate(NavigationTree.FavoriteImages.name + "/$bread")
                    }
                )
            }
            composable(
                NavigationTree.Breed.name + "/{breed}",
                arguments = listOf(navArgument("breed") { type = NavType.StringType })
            ) { backStackEntry ->
                BreedDetailScreen(
                    backStackEntry.arguments?.getString("breed") ?: "",
                    onClickBack = { navController.popBackStack() },
                )
            }
            composable(
                NavigationTree.FavoriteImages.name + "/{breed}",
                arguments = listOf(navArgument("breed") { type = NavType.StringType })
            ) { backStackEntry ->
                FavoriteImagesScreen(
                    backStackEntry.arguments?.getString("breed") ?: "",
                    onClickBack = { navController.popBackStack() },
                )
            }

        }
    }
}
