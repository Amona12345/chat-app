package com.example.chatapplication.utills

import com.example.chatapplication.model.AppUser
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

object FirebaseUtils {
    fun addUser(user: AppUser,
                onSuccessListener: OnSuccessListener<Void>,
                onFailureListener: OnFailureListener){
        Firebase.firestore.collection(AppUser.COLLECTION_NAME)
            .document(user.uid!!).set(user)
            .addOnSuccessListener(onSuccessListener)
            .addOnFailureListener(onFailureListener)

    }
}