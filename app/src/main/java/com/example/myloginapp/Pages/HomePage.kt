package com.example.myloginapp.Pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myloginapp.AuthState
import com.example.myloginapp.AuthViewModel

@Composable
fun HomePage(modifier: Modifier = Modifier,navController: NavController,authViewModel: AuthViewModel){

   val authState = authViewModel.authState.observeAsState()
   
   LaunchedEffect(authState.value) {
      when(authState.value){
          is AuthState.Unauthenticated -> navController.navigate("login")
         else-> Unit
      }
   }

   Column(modifier = Modifier.fillMaxSize(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally) {

       Spacer(modifier = Modifier.height(20.dp))
             Text("Home Page", fontSize = 32.sp)
       Spacer(modifier = Modifier.height(10.dp))
      TextButton(onClick = {authViewModel.signout()}) {
           Text("Sign Out")
      }
   }

}