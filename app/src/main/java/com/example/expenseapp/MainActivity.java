package com.example.expenseapp;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /* Declaration of Resources */

    ArrayList<Transaction> transactionList;  // Store transactions
    TransactionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        transactionList = new ArrayList<>();

        /* Click Listener on Profile button */
        Button btProfile = findViewById(R.id.btnUserProfile);
        btProfile.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AuthenticationActivity.class);
            startActivity(intent);
        });

        /* Click Listener on Add new Bill */
        ImageButton btAddBill = findViewById(R.id.btnAddTransaction);
        btAddBill.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddExpenseActivity.class);
            startActivity(intent);
        });

        Intent getExpenseDetail = getIntent();
        String description = getExpenseDetail.getStringExtra("description");
        if (description == null) description = "No Description";
        Double amount = getExpenseDetail.getDoubleExtra("amount", 0.0);


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String formattedDateTime = LocalDateTime.now().format(formatter);
        transactionList.add(new Transaction(description, amount, formattedDateTime));

        RecyclerView recycleView = findViewById(R.id.recycleListView);
        TextView tvRecentTransactionMessage = findViewById(R.id.tvNoTransactionMessage);
        if (transactionList.isEmpty()) {
            tvRecentTransactionMessage.setVisibility(VISIBLE);
            recycleView.setVisibility(GONE);

        } else {
            recycleView.setVisibility(VISIBLE);
            tvRecentTransactionMessage.setVisibility(GONE);
            adapter = new TransactionAdapter(transactionList);

            recycleView.setLayoutManager(new LinearLayoutManager(this));
            recycleView.setAdapter(adapter);
        }
    }
}
