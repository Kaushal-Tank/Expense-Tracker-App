package com.example.expensetracker

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    /* Initialize Firebase Database */
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        /*  Layouts  */

        // Sign In
        val signInLayout = findViewById<ConstraintLayout>(R.id.clSignInLayout)
        // Sign Up
        val signUpLayout = findViewById<ConstraintLayout>(R.id.clSignUpLayout)


        /* Go to Sign In Page */
        val goSignInPage = findViewById<Button>(R.id.btnGoSignIn)
        goSignInPage.setOnClickListener {
            signInLayout.visibility = View.VISIBLE
            signUpLayout.visibility = View.GONE
        }

        /* Go to Sign Up Page */
        val goSignUpPage = findViewById<Button>(R.id.btnGoSignUp)
        goSignUpPage.setOnClickListener {
            signInLayout.visibility = View.GONE
            signUpLayout.visibility = View.VISIBLE
        }

        /* Send data to firebase for create account */
        val btSignUp = findViewById<Button>(R.id.btnSignUp)
        btSignUp.setOnClickListener {
            val name = findViewById<TextInputEditText>(R.id.itUserNameSU).text.toString()
            val email = findViewById<TextInputEditText>(R.id.itEmailSU).text.toString()
            val password = findViewById<TextInputEditText>(R.id.itPasswordSU).text.toString()

            saveDataToFirebase(
                name = name,
                email = email,
                password = password
            )
        }

        /* Sign In */
        val btSignIn = findViewById<Button>(R.id.btnSignIn)
        btSignIn.setOnClickListener {
            val email = findViewById<TextInputEditText>(R.id.itEmailSI).text.toString()
            val password = findViewById<TextInputEditText>(R.id.itPasswordSI).text.toString()

            getDataToFirebase(email = email, password = password)
        }


    }

    /* Fun for save data in firebase */
    private fun saveDataToFirebase(name: String, email: String, password: String) {

        val userData = hashMapOf(
            "name" to name,
            "email" to email,
            "password" to password
        )

        db.collection("users")
            .document(email)
            .set(userData)
            .addOnSuccessListener {
                Toast.makeText(this, "Account create successfully", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }

    }

    /* Fun for get data from firebase */
    private fun getDataToFirebase(email: String, password: String) {

        db.collection("users")
            .document(email)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val dbPass = document.getString("password")

                    if (dbPass == password) Toast.makeText(
                        this,
                        "Account Access",
                        Toast.LENGTH_SHORT
                    ).show()
                    else Toast.makeText(
                        this,
                        "Wrong Password",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this,
                        "Email Not Found",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(
                    this,
                    "Error: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }

    }
}
