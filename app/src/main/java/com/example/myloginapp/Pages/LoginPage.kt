package com.example.myloginapp.Pages

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myloginapp.AuthState
import com.example.myloginapp.AuthViewModel

@Composable
fun LoginPage(modifier: Modifier = Modifier,navController: NavController,authViewModel: AuthViewModel){
    var email by remember{ mutableStateOf("") }
    var Password by remember{ mutableStateOf("") }

    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current


    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Authenticated-> navController.navigate("homePage")
            is AuthState.Error -> Toast.makeText(context,
                (authState.value as AuthState.Error).message, Toast.LENGTH_SHORT).show()
            else-> Unit
        }

    }


    Column (modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Login Page", fontSize = 32.sp, fontWeight = FontWeight.W800,
            color = Color.Black, textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(value = email, onValueChange = {
           email = it
        },
            label = {
                Text(text = "Email")
            })
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(value = Password, onValueChange = {
            Password = it
        },
            label = {
                Text(text = "Password")
            })
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {
            authViewModel.login(email, Password)
        }, enabled = authState.value != AuthState.Loding, colors = ButtonDefaults.buttonColors(Color.Red)) {
            Text(text = "Login")
        }
        Spacer(modifier = Modifier.height(20.dp))
        TextButton(onClick = {navController.navigate("signupPage")}) {
            Text("Don't Have An Account,Signup", color = Color.Blue, fontSize = 15.sp)
        }
    }
}
