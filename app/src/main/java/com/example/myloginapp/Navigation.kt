package com.example.myloginapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myloginapp.Pages.HomePage
import com.example.myloginapp.Pages.LoginPage
import com.example.myloginapp.Pages.SignupPage
import java.util.stream.DoubleStream.builder

@Composable
fun Navigate(modifier: Modifier = Modifier,authViewModel: AuthViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login",builder = {
        composable("login") {
            LoginPage(modifier,navController, authViewModel)
        }
        composable("homePage") {
            HomePage(modifier,navController, authViewModel)
        }
        composable("signupPage"){
            SignupPage(modifier,navController, authViewModel)
        }
    })
}