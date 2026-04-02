package com.example.expensetracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import android.view.View

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

        /* ---------- Initialize Classes ---------- */
        val signInClass = SignInActivity()
        val signUpClass = SignUpActivity()

        /* ---------- Layouts ---------- */
        val signInLayout = findViewById<ConstraintLayout>(R.id.clSignInLayout)
        val signUpLayout = findViewById<ConstraintLayout>(R.id.clSignUpLayout)

        /* ---------- Buttons ---------- */
        val signInBtn = findViewById<Button>(R.id.btnSignIn)
        val signUpBtn = findViewById<Button>(R.id.btnSignUp)
        val goToSignIn = findViewById<Button>(R.id.btnGoSignIn)
        val goToSignUp = findViewById<Button>(R.id.btnGoSignUp)

        /* ---------- Sign in User ---------- */
        signInBtn.setOnClickListener {
            val email = findViewById<TextInputEditText>(R.id.itEmailSI)
            val password = findViewById<TextInputEditText>(R.id.itPasswordSI)

            signInClass.signInUser(
                email.text.toString(),
                password.text.toString()
            ) { success, message ->
                if (success) {
                    Toast.makeText(this, "Sign In Success", Toast.LENGTH_SHORT).show()
                    startActivity(
                        Intent(
                            this, HomeActivity::class.java
                        )
                    )
                } else {
                    Toast.makeText(this, message ?: "Sign In Failed !", Toast.LENGTH_SHORT).show()
                }
            }
            email.setText("")
            password.setText("")
        }

        /* ---------- Sign Up User ---------- */
        signUpBtn.setOnClickListener {
            val name = findViewById<TextInputEditText>(R.id.itUserNameSU)
            val email = findViewById<TextInputEditText>(R.id.itEmailSU)
            val password = findViewById<TextInputEditText>(R.id.itPasswordSU)

            signUpClass.signUpUser(
                name.text.toString(),
                email.text.toString(),
                password.text.toString(),
            ) { success, message ->

                if (success) {
                    Toast.makeText(this, "Sign Up Completed", Toast.LENGTH_SHORT).show()
                    startActivity(
                        Intent(
                            this,
                            HomeActivity::class.java
                        )
                    )
                } else {
                    Toast.makeText(this, message ?: "Sign Up Failed", Toast.LENGTH_SHORT).show()
                }
            }
            name.setText("")
            email.setText("")
            password.setText("")
        }

        /* ----------  Go to Sign Up to Sign In ---------- */
        goToSignIn.setOnClickListener {
            signUpLayout.visibility = View.GONE
            signInLayout.visibility = View.VISIBLE
        }


        /* ----------  Go to Sign In to Sign Up ---------- */
        goToSignUp.setOnClickListener {
            signUpLayout.visibility = View.VISIBLE
            signInLayout.visibility = View.GONE
        }

    }
}


