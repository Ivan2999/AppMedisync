package com.example.appmedisync.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appmedisync.views.loginScreen
import com.example.appmedisync.views.mainScreen
import com.example.appmedisync.views.registroMedico
import com.example.appmedisync.views.registroPaciente

@Composable
fun NavManager() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "Login"
    ) {
        composable("Login") {
            loginScreen(navController)
        }
        composable("Registrar Paciente") {
            registroPaciente(navController)
        }
        composable("Registrar Medico") {
            registroMedico(navController)
        }
        composable("MainScreen") {
            mainScreen(navController)
        }

    }
}