package com.una.modus.presentation.common.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.una.modus.presentation.auth.AccountCreatedScreen
import com.una.modus.presentation.auth.ForgotPasswordScreen
import com.una.modus.presentation.auth.LoginScreen
import com.una.modus.presentation.auth.NewPasswordScreen
import com.una.modus.presentation.auth.RegisterScreen
import com.una.modus.presentation.auth.VerifyEmailScreen
import com.una.modus.presentation.common.SplashScreen
import com.una.modus.presentation.lists.GenericListScreen
import com.una.modus.presentation.menu.HomeScreen

/**
 * Rutas de la app
 *
 * Enumera cada pantalla como una subclase con su ruta de navegación
 * (string usado por Jetpack Navigation). Agregar una pantalla nueva
 * empieza por declarar su ruta acá y luego registrar su `composable(...)`
 * en [ModusNavGraph].
 */
sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object Login : Screen("login")
    data object Register : Screen("register")
    data object VerifyEmail : Screen("verify_email")
    data object AccountCreated : Screen("account_created")
    data object ForgotPassword : Screen("forgot_password")
    data object NewPassword : Screen("new_password")
    data object Home : Screen("home")
    data object GenericList : Screen("list")
}

/**
 * Grafo de navegación
 *
 * Conecta cada [Screen] con su composable de pantalla y define a dónde
 * navega cada acción del usuario (botones, links, "volver"). Es el único
 * lugar donde las pantallas se conocen entre sí: cada pantalla solo recibe
 * lambdas `onXxx`, sin saber a qué ruta concreta llevan.
 *
 * Flujo implementado:
 * `Splash → Login ⇄ Registro → Verificar correo → Cuenta creada → Home`
 * y `Login → Recuperar contraseña → Nueva contraseña → Login`.
 */
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
        // Splash: se muestra una vez y se reemplaza a sí mismo por Login
        // (popUpTo inclusive) para que "atrás" no vuelva a mostrarlo.
        composable(Screen.Splash.route) {
            SplashScreen(
                onFinished = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.Login.route) {
            LoginScreen(
                onBack = { navController.popBackStack() },
                onLoginSuccess = { navController.navigate(Screen.Home.route) },
                onForgotPassword = { navController.navigate(Screen.ForgotPassword.route) },
                onNavigateToRegister = { navController.navigate(Screen.Register.route) }
            )
        }
        composable(Screen.Register.route) {
            RegisterScreen(
                onBack = { navController.popBackStack() },
                onNavigateToVerifyEmail = { navController.navigate(Screen.VerifyEmail.route) }
            )
        }
        composable(Screen.VerifyEmail.route) {
            VerifyEmailScreen(
                onBack = { navController.popBackStack() },
                onVerified = { navController.navigate(Screen.AccountCreated.route) },
                onResendCode = {}
            )
        }
        // Cuenta creada: al presionar "Comenzar" se limpia todo el stack de
        // auth hasta Login (inclusive) para que "atrás" desde Home cierre la app.
        composable(Screen.AccountCreated.route) {
            AccountCreatedScreen(
                onBack = { navController.popBackStack() },
                onStart = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.ForgotPassword.route) {
            ForgotPasswordScreen(
                onBack = { navController.popBackStack() },
                onLinkSent = { navController.navigate(Screen.NewPassword.route) },
                onBackToLogin = {
                    navController.popBackStack(Screen.Login.route, inclusive = false)
                }
            )
        }
        composable(Screen.NewPassword.route) {
            NewPasswordScreen(
                onBack = { navController.popBackStack() },
                onPasswordSaved = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.Home.route) {
            HomeScreen()
        }
        composable(Screen.GenericList.route) {
            GenericListScreen()
        }
    }
}
