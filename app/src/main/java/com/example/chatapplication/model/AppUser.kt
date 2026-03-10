package com.example.chatapplication.model

data class AppUser(
    val firstName: String? = null,
    val email: String? = null,
    val uid :String ? =null
){
    companion object{
        val COLLECTION_NAME ="Users"
    }
}
