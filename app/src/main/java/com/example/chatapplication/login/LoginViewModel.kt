package com.example.chatapplication.login

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.chatapplication.model.AppUser
import com.example.chatapplication.register.RegisterEvents
import com.example.chatapplication.splash.SplashEvents
import com.example.chatapplication.utills.FirebaseUtils
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class LoginViewModel: ViewModel(){
    val emailState = mutableStateOf("")
    val passwordState = mutableStateOf("")
    val emailErrorState = mutableStateOf<String?>(null)
    val passwordErrorState = mutableStateOf<String?>(null)
    val events = mutableStateOf<LoginEvents>( LoginEvents.Idle)
    val auth = Firebase.auth
    val isLoading = mutableStateOf(false)
    val message = mutableStateOf("")
fun resetEvents(){
    events.value = LoginEvents.Idle
}

    fun navigateToRegister (){
        events.value = LoginEvents.NavigateToRegister
    }
    fun login() {
        if (validateFields()) {
            //Communicate with firebase
            isLoading.value=true
            auth.signInWithEmailAndPassword(emailState.value, passwordState.value)
                .addOnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.e("TAG", "Error Occured :${task.exception?.localizedMessage}")
                        isLoading.value=false
                        message.value = task.exception?.message?:"Error Occurred"
                        return@addOnCompleteListener
                    }
                    val uid = task.result.user?.uid
                    //Save user into Firebase Cloud Firestore
                    //add data to Fires store
                    getUserFromFireStore(uid!!)

                }

        }

    }

    fun getUserFromFireStore(uid: String) {
        FirebaseUtils.getUser(uid, onSuccessListener = { documentSnapshot ->
            isLoading.value=
                false
            val user = documentSnapshot.toObject(AppUser::class.java)
            navigateToHome(user!!)
        }, onFailureListener = {
isLoading.value=false
            Log.e("TAG", "Error Occured :${it.localizedMessage}")
        })

    }

    fun validateFields(): Boolean {
        if (emailState.value.isEmpty() || emailState.value.isBlank()) {
            emailErrorState.value = "email is required"
            return false
        } else emailErrorState.value = null
        if (passwordState.value.isEmpty() || passwordState.value.isBlank()) {
            passwordErrorState.value = "password is required"
            return false
        } else passwordErrorState.value = null
        if (!Patterns.EMAIL_ADDRESS.matcher(emailState.value).matches()) {
            emailErrorState.value = "invalid email"
            return false
        } else emailErrorState.value = null

        return true
    }

    fun navigateToHome(user: AppUser) {
        events.value = LoginEvents.NavigateToHome(user)
    }

}