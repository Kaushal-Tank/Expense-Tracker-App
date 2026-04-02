package com.example.expensetracker

import android.content.Context
import android.widget.Toast
import com.example.expensetracker.model.Expense
import com.example.expensetracker.model.User
import com.google.firebase.firestore.FirebaseFirestore

class SignInActivity {

    /* ---------- Connection and Auth to FireBase ---------- */
    private val auth = com.google.firebase.auth.FirebaseAuth.getInstance()

    /* ---------- Authenticate User ---------- */
    fun signInUser(email: String, password: String, onResult: (Boolean, String?) -> Unit) {

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true, null)
                } else {
                    val message = when (task.exception) {
                        is com.google.firebase.auth.FirebaseAuthInvalidUserException ->
                            "User Not Found !"

                        is com.google.firebase.auth.FirebaseAuthInvalidCredentialsException ->
                            "Wrong Password !"

                        else -> "Sign In Failed !"
                    }
                    onResult(false, message)
                }
            }

    }
}