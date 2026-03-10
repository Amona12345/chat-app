package com.example.chatapplication.register

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.chatapplication.login.LoginEvents
import com.example.chatapplication.model.AppUser
import com.example.chatapplication.utills.FirebaseUtils
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class RegisterViewModel : ViewModel() {
    val firstNameState = mutableStateOf("")
    val firstNameErrorState = mutableStateOf<String?>(null)
    val emailState = mutableStateOf("")
    val passwordState = mutableStateOf("")
    val emailErrorState = mutableStateOf<String?>(null)
    val passwordErrorState = mutableStateOf<String?>(null)
    val events = mutableStateOf<RegisterEvents>(RegisterEvents.Idle)

    val auth = Firebase.auth
    fun register() {
        if (validateFields()) {
            //Communicate with firebase
            auth.createUserWithEmailAndPassword(emailState.value, passwordState.value)
                .addOnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.e("TAG", "Error Occured :${task.exception?.localizedMessage}")
                        return@addOnCompleteListener
                    }
                    val uid = task.result.user?.uid
                    //Save user into Firebase Cloud Firestore
                    //add data to Fires store
                    addUserToFireStore(uid!!)

                }

        }

    }

    fun addUserToFireStore(uid: String) {
        val user = AppUser(firstNameState.value, emailState.value, uid)
        FirebaseUtils.addUser(user, onSuccessListener = {

            navigateToHome(user)
        }, onFailureListener = {

        })

    }

    fun validateFields(): Boolean {
        if (firstNameState.value.isEmpty() || firstNameState.value.isBlank()) {
            firstNameErrorState.value = "first name is required"
            return false

        } else
            firstNameErrorState.value = null
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
        events.value = RegisterEvents.NavigateToHome(user)
    }

}