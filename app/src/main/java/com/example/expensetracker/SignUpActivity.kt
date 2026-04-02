package com.example.expensetracker

import com.google.firebase.firestore.FirebaseFirestore


class SignUpActivity {

    /* ---------- Connection and Auth to FireBase ---------- */
    private val db = FirebaseFirestore.getInstance()
    private val auth = com.google.firebase.auth.FirebaseAuth.getInstance()

    /* ---------- Save User in Database ---------- */
    fun signUpUser(
        name: String,
        email: String,
        password: String,
        onResult: (Boolean, String?) -> Unit
    ) {

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val uid = auth.currentUser?.uid ?: return@addOnCompleteListener

                    val user = hashMapOf(
                        "name" to name,
                        "email" to email,
                        "balance" to 0.0
                    )

                    db.collection("user")
                        .document(uid)
                        .set(user)
                        .addOnSuccessListener {
                            onResult(true, null)
                        }
                        .addOnFailureListener {
                            onResult(false, it.message)
                        }

                } else {
                    val message = when (task.exception) {
                        is com.google.firebase.auth.FirebaseAuthUserCollisionException ->
                            "User already exist !"

                        is com.google.firebase.auth.FirebaseAuthWeakPasswordException ->
                            "Weak Password !"

                        is com.google.firebase.auth.FirebaseAuthInvalidCredentialsException ->
                            "Wrong Email formate !"

                        else -> "Sign up Failed !"
                    }
                    onResult(false, message)
                }
            }

    }
}
