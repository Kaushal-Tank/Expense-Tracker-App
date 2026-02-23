package com.example.expenseapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class AuthenticationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_authentication);
        ConstraintLayout loginLinear = findViewById(R.id.clLogIn);
        ConstraintLayout signInLinear = findViewById(R.id.clSignIn);

        /* LogIn Button */
        Button btnSILogIn = findViewById(R.id.btnSILogIn);
        Button btnLILogIn = findViewById(R.id.btnLILogIn);

        /* SignIn Button */
        Button btnSISignIn = findViewById(R.id.btnSISignIn);
        Button btnLISignIn = findViewById(R.id.btnLISignIn);

        /* set common click Listener on btnSILogIn and btnLILogIn */
        View.OnClickListener commonLogInBtn = view -> {
            signInLinear.setVisibility(View.GONE);
            loginLinear.setVisibility(View.VISIBLE);
        };

        /* set common click Listener on btnSISignIn and btnLISignIn */
        View.OnClickListener commonSignInBtn = view -> {
            signInLinear.setVisibility(View.VISIBLE);
            loginLinear.setVisibility(View.GONE);
        };

        // Set Click Listener on both SignIn buttons
        btnLISignIn.setOnClickListener(commonSignInBtn);
        btnSISignIn.setOnClickListener(commonSignInBtn);

        // Set Click Listener on both LogIn buttons
        btnSILogIn.setOnClickListener(commonLogInBtn);
        btnLILogIn.setOnClickListener(commonLogInBtn);

    }
}