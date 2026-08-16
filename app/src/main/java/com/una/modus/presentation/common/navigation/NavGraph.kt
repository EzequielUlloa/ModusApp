package com.una.modus.presentation.common.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.una.modus.presentation.auth.LoginScreen
import com.una.modus.presentation.auth.RegisterScreen
import com.una.modus.presentation.common.SplashScreen
import com.una.modus.presentation.lists.GenericListScreen
import com.una.modus.presentation.menu.HomeScreen

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object Login : Screen("login")
    data object Register : Screen("register")
    data object Home : Screen("home")
    data object GenericList : Screen("list")
}

@Composable
fun ModusNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Splash.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Screen.Splash.route) {
            SplashScreen()
        }
        composable(Screen.Login.route) {
            LoginScreen()
        }
        composable(Screen.Register.route) {
            RegisterScreen()
        }
        composable(Screen.Home.route) {
            HomeScreen()
        }
        composable(Screen.GenericList.route) {
            GenericListScreen()
        }
    }
}
