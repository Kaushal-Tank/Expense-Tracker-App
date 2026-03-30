package com.example.expensetracker

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Call Class for run data query
        val dataQuery = FunctionsForData()
        /*  ------------------------------------------------------------------------------  */

        /* ---------- Layouts ---------- */
        // Sign In
        val signInLayout = findViewById<ConstraintLayout>(R.id.clSignInLayout)
        // Sign Up
        val signUpLayout = findViewById<ConstraintLayout>(R.id.clSignUpLayout)
        /*  ------------------------------------------------------------------------------  */

        /* ---------- Buttons ---------- */
        // Sign In Button
        val btSignIn = findViewById<Button>(R.id.btnSignIn)
        // Sign Up Button
        val btSignUp = findViewById<Button>(R.id.btnSignUp)
        /*  ------------------------------------------------------------------------------  */

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

        /* Send data to firebase for create account and go to Home page */
        btSignUp.setOnClickListener {
            val name = findViewById<TextInputEditText>(R.id.itUserNameSU).text.toString()
            val email = findViewById<TextInputEditText>(R.id.itEmailSU).text.toString()
            val password = findViewById<TextInputEditText>(R.id.itPasswordSU).text.toString()

            dataQuery.saveDataToFirebase(
                name = name,
                email = email,
                password = password,
                context = this
            )
            val goHomePage = Intent(this, HomeActivity::class.java)
            startActivity(goHomePage)


        }

        /* Sign In and go to Home page */
        btSignIn.setOnClickListener {
            val email = findViewById<TextInputEditText>(R.id.itEmailSI).text.toString()
            val password = findViewById<TextInputEditText>(R.id.itPasswordSI).text.toString()

            dataQuery.checkUserData(email = email, password = password, context = this)
            val goHomePage = Intent(this, HomeActivity::class.java)
            startActivity(goHomePage)
        }

    }
}


