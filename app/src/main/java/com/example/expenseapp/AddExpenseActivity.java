package com.example.expenseapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class AddExpenseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_expense);

        EditText etDetail = findViewById(R.id.expenseDetail);
        EditText etAmount = findViewById(R.id.amount);
        Button btAdd = findViewById(R.id.addExpanse);

        btAdd.setOnClickListener(view -> {
            String expenseDetail = etDetail.getText().toString();
            String stAmount = etAmount.getText().toString().trim();
            double amount = 0.0;
            if (!stAmount.isEmpty()) {
                amount = Double.parseDouble(stAmount);
            }

            // Send Expense Details to Main Activity
            Intent resultIntent = new Intent(AddExpenseActivity.this, MainActivity.class);
            resultIntent.putExtra("description",expenseDetail);
            resultIntent.putExtra("amount",amount);

            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}