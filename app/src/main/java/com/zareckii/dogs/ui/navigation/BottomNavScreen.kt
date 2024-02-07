package com.zareckii.dogs.ui.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.zareckii.dogs.R

@Composable
fun BottomNavScreen(
    modifier: Modifier = Modifier,
    bottomNavViewModel: BottomNavViewModel = hiltViewModel()
) {
    val navController = rememberNavController()

    val scaffoldState = rememberScaffoldState()

    val bottomBarTab = remember {
        listOf(
            ScreenBottomNav.Breeds,
            ScreenBottomNav.Favourites,
        )
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val currentRoute = currentDestination?.route ?: ""
    val shouldShowBottomBar = bottomBarTab.any { currentRoute == it.route }
    val isOffline by bottomNavViewModel.isOffline.collectAsStateWithLifecycle()
    val noConnection = stringResource(R.string.no_internet_connection)

    LaunchedEffect(isOffline) {

        if (isOffline) {
            val result = scaffoldState.snackbarHostState.showSnackbar(
                message = noConnection,
                duration = SnackbarDuration.Indefinite
            )

            when (result) {
                SnackbarResult.ActionPerformed -> {}
                else -> {}
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
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
