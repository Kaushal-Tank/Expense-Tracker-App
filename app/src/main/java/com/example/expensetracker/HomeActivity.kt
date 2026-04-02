package com.example.expensetracker

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.expensetracker.model.UserRepo

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        /* ---------- Initialize Classes  ---------- */
        val userRepo = UserRepo()

        /* ---------- Set name and balance in card ---------- */
        val userName = findViewById<TextView>(R.id.tvUserName)
        val balance = findViewById<TextView>(R.id.tvBalance)

        userRepo.getUserData(
            onSuccess = { user ->
                userName.text = user.name
                balance.text = user.balance.toString()
            },
            onFailer = {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        )
    }
}