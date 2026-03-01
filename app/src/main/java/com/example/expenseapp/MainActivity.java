package com.example.expenseapp;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /* Declaration of Resources */

    ArrayList<Transaction> transactionList = new ArrayList<>();
    // Store transactions
    TransactionAdapter adapter;
    private ActivityResultLauncher<Intent> addExpenseLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        addExpenseLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK && result.getData() != null) {

                Intent data = result.getData();

                String description = data.getStringExtra("description");
                double amount = data.getDoubleExtra("amount", 0.0);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

                String formattedDateTime = LocalDateTime.now().format(formatter);

                Transaction newTransaction = new Transaction(description, amount, formattedDateTime);
                transactionList.add(newTransaction);
                adapter.notifyItemInserted(transactionList.size() - 1);

                updateTotalAmount();
                updateUI();
            }
        });


        RecyclerView recycleView = findViewById(R.id.recycleListView);
        TextView tvRecentTransactionMessage = findViewById(R.id.tvNoTransactionMessage);

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
            addExpenseLauncher.launch(intent);
        });


        // Show Transaction Box
        recycleView.setVisibility(VISIBLE);
        tvRecentTransactionMessage.setVisibility(GONE);
        adapter = new TransactionAdapter(transactionList);

        recycleView.setLayoutManager(new LinearLayoutManager(this));
        recycleView.setAdapter(adapter);
        updateUI();

    }

    private void updateTotalAmount() {

        double sumOfAmount = 0.0;

        for (Transaction t : transactionList) {
            sumOfAmount += t.getAmount();
        }

        TextView tvAmount = findViewById(R.id.tvAmount);
        tvAmount.setText(String.valueOf(sumOfAmount));
    }

    private void updateUI() {
        RecyclerView recyclerView = findViewById(R.id.recycleListView);
        TextView textView = findViewById(R.id.tvNoTransactionMessage);

        if (transactionList.isEmpty()){
            recyclerView.setVisibility(GONE);
            textView.setVisibility(VISIBLE);
        } else {
            recyclerView.setVisibility(VISIBLE);
            textView.setVisibility(GONE);
        }
    }
}
