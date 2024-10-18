package com.example.notesapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notesapp.view.login.TabsView
import com.example.notesapp.view.notes.HomeView
import com.example.notesapp.viewmodel.LoginViewModel
import com.example.notesapp.viewmodel.NotesViewModel

@Composable
fun NavManager(loginViewModel: LoginViewModel, NotesViewModel: NotesViewModel) {
    val navController = rememberNavController()
    NavHost(navController= navController, startDestination = "login") {
        composable("login") {
            TabsView(navController = navController, loginViewModel = loginViewModel)
        }
        composable("home") {
            HomeView(navController = navController, viewModel = NotesViewModel)
        }
    }

}