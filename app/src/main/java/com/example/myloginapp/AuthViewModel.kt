package com.example.myloginapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel: ViewModel() {
    private val auth : FirebaseAuth = FirebaseAuth.getInstance()
    private val _authState = MutableLiveData<AuthState>()
    val authState : LiveData<AuthState> = _authState

    init {
        checkAuthStatus()
    }
    fun checkAuthStatus(){
        if(auth.currentUser==null){
            _authState.value = AuthState.Unauthenticated
        }else{
            _authState.value = AuthState.Authenticated
        }
    }

    fun login(email: String,Password: String){

        if(email.isEmpty() || Password.isEmpty()){
            _authState.value = AuthState.Error("Email or Password can't be empty")
            return
        }
        _authState.value = AuthState.Loding
        auth.signInWithEmailAndPassword(email,Password)
            .addOnCompleteListener{task->
                 if(task.isSuccessful){
                     _authState.value = AuthState.Authenticated
                 }else{
                   _authState.value = AuthState.Error(task.exception?.message?:"Something went wrong")
                 }

            }
    }
    fun signup(email: String,Password: String){

        if(email.isEmpty() || Password.isEmpty()){
            _authState.value = AuthState.Error("Email or Password can't be empty")
            return
        }
        _authState.value = AuthState.Loding
        auth.createUserWithEmailAndPassword(email,Password)
            .addOnCompleteListener{task->
                if(task.isSuccessful){
                    _authState.value = AuthState.Authenticated
                }else{
                    _authState.value = AuthState.Error(task.exception?.message?:"Something went wrong")
                }

            }
    }

    fun signout(){
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
    }

}

sealed class AuthState{
    object Authenticated : AuthState()
    object  Unauthenticated : AuthState()
    object Loding : AuthState()
    data class Error(val message : String): AuthState()
}