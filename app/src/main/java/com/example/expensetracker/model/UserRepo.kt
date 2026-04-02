package com.example.expensetracker.model

import com.google.firebase.firestore.FirebaseFirestore

class UserRepo {

    /* ---------- Connection and Auth to FireBase ---------- */
    private val db = FirebaseFirestore.getInstance()
    private val auth = com.google.firebase.auth.FirebaseAuth.getInstance()

    fun getUserData(
        onSuccess: (User) -> Unit,
        onFailer: (String) -> Unit
    ) {
        val uid = auth.currentUser?.uid ?: return onFailer("User not logged in !")

        db.collection("user")
            .document(uid)
            .get()
            .addOnSuccessListener { document ->
                val user = document.toObject(User::class.java)
                if (user != null) onSuccess(user)
                else onFailer("User data not found !")
            }
            .addOnFailureListener { error ->
                onFailer(error.message ?: "Error")
            }
    }

}