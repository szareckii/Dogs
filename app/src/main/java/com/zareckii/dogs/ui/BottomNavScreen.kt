package com.zareckii.dogs.ui

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.zareckii.dogs.navigation.AppNavGraph
import com.zareckii.dogs.navigation.ScreenBottomNav

@Composable
fun BottomNavScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    val bottomBarTab = remember {
        listOf(
            ScreenBottomNav.Breeds,
            ScreenBottomNav.Favorites,
        )
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val currentRoute = currentDestination?.route ?: ""
    val shouldShowBottomBar = bottomBarTab.any { currentRoute == it.route }

    Scaffold(
        bottomBar = {
            if (shouldShowBottomBar) {

                    BottomNavigation(modifier = modifier) {
                        bottomBarTab.forEach { screen ->
                            BottomNavigationItem(
                                icon = {
                                    Icon(
                                        ImageVector.vectorResource(screen.icon),
                                        contentDescription = null
                                    )
                                },
                                label = { Text(stringResource(screen.title)) },
                                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                onClick = {
                                    navController.navigate(screen.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            )
                        }
                    }
                }
        }
    ) { innerPadding ->
        AppNavGraph(navController = navController, innerPadding = innerPadding)
    }
}
