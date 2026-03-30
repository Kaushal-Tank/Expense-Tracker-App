package com.example.expensetracker

import android.content.Context
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class FunctionsForData {

    /* Initialize Firebase Database */
    private val db = FirebaseFirestore.getInstance()

    /* Fun for get data from firebase */
    fun checkUserData(email: String, password: String, context: Context) {

        db.collection("users")
            .document(email)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val dbPass = document.getString("password")

                    if (dbPass == password) Toast.makeText(
                        context,
                        "Account Access",
                        Toast.LENGTH_SHORT
                    ).show()
                    else Toast.makeText(
                        context,
                        "Wrong Password",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        context,
                        "Email Not Found",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(
                    context,
                    "Error: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }

    }

    /* Fun for save data in firebase */
    fun saveDataToFirebase(name: String, email: String, password: String, context: MainActivity) {

        val userData = hashMapOf(
            "name" to name,
            "email" to email,
            "password" to password
        )

        db.collection("users")
            .document(email)
            .set(userData)
            .addOnSuccessListener {
                Toast.makeText(context, "Account create successfully", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }

    }

    /* Fun for get all Data from Database */
//    fun getAllData()  continue from here


}