package com.una.modus.presentation.common.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.una.modus.presentation.auth.login.AccountCreatedScreen
import com.una.modus.presentation.auth.login.ForgotPasswordScreen
import com.una.modus.presentation.auth.login.LoginScreen
import com.una.modus.presentation.auth.login.NewPasswordScreen
import com.una.modus.presentation.auth.login.RegisterScreen
import com.una.modus.presentation.auth.login.VerifyEmailScreen
import com.una.modus.presentation.auth.onboarding.OnboardingMethodScreen
import com.una.modus.presentation.auth.onboarding.OnboardingPhotoScreen
import com.una.modus.presentation.auth.onboarding.OnboardingTeachingScreen
import com.una.modus.presentation.common.SplashScreen
import com.una.modus.presentation.lists.CursosScreen
import com.una.modus.presentation.lists.ElegirCursoScreen
import com.una.modus.presentation.lists.GenericListScreen
import com.una.modus.presentation.lists.HistorialScreen
import com.una.modus.presentation.menu.AvisosScreen
import com.una.modus.presentation.menu.HomeScreen
import com.una.modus.presentation.menu.PerfilScreen

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
    data object OnboardingPhoto : Screen("onboarding_photo")
    data object OnboardingMethod : Screen("onboarding_method")
    data object OnboardingTeaching : Screen("onboarding_teaching")
    data object ForgotPassword : Screen("forgot_password")
    data object NewPassword : Screen("new_password")
    data object Home : Screen("home")
    data object Cursos : Screen("cursos")
    data object Avisos : Screen("avisos")
    data object Perfil : Screen("perfil")
    data object GenericList : Screen("list")
    data object ElegirCurso : Screen("elegir_curso")
    data object Historial : Screen("historial/{cursoId}") {
        fun route(cursoId: String) = "historial/$cursoId"
    }
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
 * `Splash → Login ⇄ Registro → Verificar correo → Cuenta creada → Onboarding
 * (Foto → Método → Enseñanza) → Home` y `Login → Recuperar contraseña →
 * Nueva contraseña → Login`.
 */
@Composable
fun ModusNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Splash.route
) {
    // Navegación entre pestañas de la barra inferior: conserva a Home en el
    // fondo del stack (saveState/restoreState) para que cambiar de pestaña
    // no acumule copias ni pierda el scroll/estado de las que ya se visitaron.
    val navigateToTab: (String) -> Unit = { route ->
        navController.navigate(route) {
            popUpTo(Screen.Home.route) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

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
                onLoginSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
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
        // auth hasta Login (inclusive) y se muestra el onboarding antes de Home.
        composable(Screen.AccountCreated.route) {
            AccountCreatedScreen(
                onBack = { navController.popBackStack() },
                onStart = {
                    navController.navigate(Screen.OnboardingPhoto.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }
        // Onboarding: se muestra una única vez, justo después de crear la
        // cuenta. "Saltar" y "Comenzar" limpian todo el stack de onboarding
        // (popUpTo OnboardingPhoto inclusive) para que "atrás" desde Home
        // cierre la app en vez de reabrir estas pantallas.
        composable(Screen.OnboardingPhoto.route) {
            OnboardingPhotoScreen(
                onNext = { navController.navigate(Screen.OnboardingMethod.route) },
                onSkip = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.OnboardingPhoto.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.OnboardingMethod.route) {
            OnboardingMethodScreen(
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate(Screen.OnboardingTeaching.route) },
                onSkip = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.OnboardingPhoto.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.OnboardingTeaching.route) {
            OnboardingTeachingScreen(
                onBack = { navController.popBackStack() },
                onStart = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.OnboardingPhoto.route) { inclusive = true }
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
            HomeScreen(
                onNavigateCursos = { navigateToTab(Screen.Cursos.route) },
                onNavigateAvisos = { navigateToTab(Screen.Avisos.route) },
                onNavigatePerfil = { navigateToTab(Screen.Perfil.route) },
                onCapturar = { navController.navigate(Screen.ElegirCurso.route) },
                onCourseClick = { cursoId -> navController.navigate(Screen.Historial.route(cursoId)) }
            )
        }
        composable(Screen.Cursos.route) {
            CursosScreen(
                onNavigateInicio = { navigateToTab(Screen.Home.route) },
                onNavigateAvisos = { navigateToTab(Screen.Avisos.route) },
                onNavigatePerfil = { navigateToTab(Screen.Perfil.route) },
                onCapturar = { navController.navigate(Screen.ElegirCurso.route) },
                onCourseClick = { cursoId -> navController.navigate(Screen.Historial.route(cursoId)) },
                onAddCourse = { navController.navigate(Screen.GenericList.route) }
            )
        }
        composable(Screen.Avisos.route) {
            AvisosScreen(
                onNavigateInicio = { navigateToTab(Screen.Home.route) },
                onNavigateCursos = { navigateToTab(Screen.Cursos.route) },
                onNavigatePerfil = { navigateToTab(Screen.Perfil.route) },
                onCapturar = { navController.navigate(Screen.ElegirCurso.route) }
            )
        }
        composable(Screen.Perfil.route) {
            PerfilScreen(
                onBack = { navController.popBackStack() },
                onLogout = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(navController.graph.id) { inclusive = true }
                    }
                },
                onNavigateInicio = { navigateToTab(Screen.Home.route) },
                onNavigateCursos = { navigateToTab(Screen.Cursos.route) },
                onNavigateAvisos = { navigateToTab(Screen.Avisos.route) },
                onCapturar = { navController.navigate(Screen.ElegirCurso.route) }
            )
        }
        // Elegir curso: paso previo a capturar, alcanzado desde el botón
        // flotante de cámara de cualquier pestaña. "Continuar" y "Otro
        // curso" quedan apuntando al placeholder de captura hasta que ese
        // flujo (05 · Captura de apunte) se implemente.
        composable(Screen.ElegirCurso.route) {
            ElegirCursoScreen(
                onBack = { navController.popBackStack() },
                onContinuar = { navController.navigate(Screen.GenericList.route) },
                onOtroCurso = { navController.navigate(Screen.GenericList.route) }
            )
        }
        // Historial de apuntes: se llega tocando un curso desde Home o la
        // pestaña Cursos. El detalle de cada apunte (16 · Detalle de
        // apunte) todavía no existe, así que su fila navega al placeholder.
        composable(
            route = Screen.Historial.route,
            arguments = listOf(navArgument("cursoId") { type = NavType.StringType })
        ) { backStackEntry ->
            val cursoId = backStackEntry.arguments?.getString("cursoId").orEmpty()
            HistorialScreen(
                cursoId = cursoId,
                onBack = { navController.popBackStack() },
                onApunteClick = { navController.navigate(Screen.GenericList.route) },
                onNavigateInicio = { navigateToTab(Screen.Home.route) },
                onNavigateCursos = { navigateToTab(Screen.Cursos.route) },
                onNavigateAvisos = { navigateToTab(Screen.Avisos.route) },
                onNavigatePerfil = { navigateToTab(Screen.Perfil.route) },
                onCapturar = { navController.navigate(Screen.ElegirCurso.route) }
            )
        }
        composable(Screen.GenericList.route) {
            GenericListScreen()
        }
    }
}
